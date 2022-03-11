package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;
import com.urise.webapp.excepcion.NotExistStorageExeption;
import com.urise.webapp.excepcion.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    //updateException
    @Test(expected = NotExistStorageExeption.class)
    public void updateException() {
        Resume testResume = new Resume(UUID_4);
        storage.update(testResume);
    }

    @Test
    public void update() throws NotExistStorageExeption {
        Resume testResume = new Resume("uuid3");
        storage.update(testResume);
        assertSame(testResume, storage.get("uuid3"));
    }

    // clear
    @Test
    public void clear() throws Exception {
        Storage testStorage = new ArrayStorage();
        storage.clear();
        assertEquals(0, storage.size());
    }

    // size
    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }


    //deleteException
    @Test(expected = NotExistStorageExeption.class)
    public void deleteException() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    //deleteException
    @Test(expected = NotExistStorageExeption.class)
    public void delete() {
        storage.clear();
        storage.delete(UUID_1);
    }

    //getAll
    @Test
    public void getAll() throws Exception {
        Resume[] test = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(test, storage.getAll());
    }

    //getException
    @Test(expected = NotExistStorageExeption.class)
    public void getException() {
        storage.get("dummy");
    }

    //get
    @Test
    public void get() throws Exception {
        assertEquals(storage.get(UUID_1), storage.get("uuid1"));
    }


    //saveException
    @Test(expected = ExistStorageException.class)
    public void saveException() {
        Resume testResume = new Resume("uuid3");
        storage.save(testResume);
    }

    //save
    @Test
    public void save() throws Exception {
        Resume testResume = new Resume(UUID_4);
        storage.save(testResume);
        assertEquals(testResume, storage.get(UUID_4));
    }

    // StorageException
    @Test(expected = StorageException.class)
    public void StorageException() {
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                Resume testResume = new Resume();
                storage.save(testResume);
            }
        } catch (StorageException e) {
            fail("Переполнение произошло раньше времени");
        }
        Resume testResume = new Resume();
        storage.save(testResume);
    }
}