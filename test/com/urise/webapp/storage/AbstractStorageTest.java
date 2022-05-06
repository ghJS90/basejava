package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;


import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.excepcion.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateException() {
        Resume testResume = new Resume(UUID_4);
        storage.update(testResume);
    }

    @Test
    public void update() throws Exception {
        storage.update(new Resume(UUID_3));
        assertEquals(new Resume(UUID_3), storage.get(UUID_3));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteException() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(storage.size(), 2);
        storage.get(UUID_1);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> test = Arrays.asList(new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3));
        assertEquals(test, storage.getAllSorted());
    }

    @Test(expected = NotExistStorageException.class)
    public void getException() {
        storage.get("dummy");
    }

    @Test
    public void get() throws Exception {
        assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = ExistStorageException.class)
    public void saveException() {
        Resume testResume = new Resume(UUID_3);
        storage.save(testResume);
    }

    @Test
    public void save() throws Exception {
        Resume testResume = new Resume(UUID_4);
        storage.save(testResume);
        assertEquals(testResume, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }

    @Test(expected = StorageException.class)
    public void StorageException() {
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                Resume testResume = new Resume(UUID_1, "Test Test");
                storage.save(testResume);
            }
        } catch (StorageException e) {
            fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume(UUID_1, "Test Test"));
    }
}