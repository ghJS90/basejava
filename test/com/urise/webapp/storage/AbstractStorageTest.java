package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume R_1 = new Resume(UUID_1, "A");
    private static final Resume R_2 = new Resume(UUID_2, "B");
    private static final Resume R_3 = new Resume(UUID_3, "C");
    private static final Resume R_4 = new Resume(UUID_4, "D");
    protected final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R_2);
        storage.save(R_1);
        storage.save(R_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateException() {
        storage.update(R_4);
    }

    @Test
    public void update() throws Exception {
        Resume testResume = new Resume(UUID_3, "F");
        storage.update(testResume);
        assertSame(testResume, storage.get(UUID_3));
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
        storage.delete(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> expected = Arrays.asList(R_1, R_2, R_3);
        assertEquals(expected, storage.getAllSorted());
    }

    @Test(expected = NotExistStorageException.class)
    public void getException() {
        storage.get("dummy");
    }

    @Test
    public void get() throws Exception {
        assertEquals(R_1, storage.get(UUID_1));
    }

    @Test(expected = ExistStorageException.class)
    public void saveException() {
        storage.save(R_3);
    }

    @Test
    public void save() throws Exception {
        storage.save(R_4);
        assertEquals(R_4, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }
}