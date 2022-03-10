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
    private int size;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    //updateException
    @Test(expected = NotExistStorageExeption.class)
    public void updateException() throws NotExistStorageExeption {
        Resume testResume = new Resume("uuid4");
        storage.update(testResume);
        assertSame(testResume, storage.get("uuid3"));
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
        assertEquals(0, size);
        assertEquals(0, storage.size());
    }

    // size
    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }


    //deleteException
    @Test(expected = NotExistStorageExeption.class)
    public void deleteException() throws Exception {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }


    //getAll
    @Test
    public void getAll() throws Exception {
        Storage testStorage = new ArrayStorage();
        testStorage.save(new Resume(UUID_1));
        testStorage.save(new Resume(UUID_2));
        testStorage.save(new Resume(UUID_3));
        assertArrayEquals(testStorage.getAll(), storage.getAll());
    }

    //getException
    @Test(expected = NotExistStorageExeption.class)
    public void getException() throws Exception {
        storage.get("dummy");
    }

    //get
    @Test
    public void get() throws Exception {
        storage.get(UUID_1);
        assertEquals(storage.get(UUID_1), storage.get("uuid1"));
    }


    //saveException
    @Test(expected = ExistStorageException.class)
    public void saveException() throws Exception {
        Resume testResume = new Resume("uuid3");
        storage.save(testResume);
    }

    //save
    @Test
    public void save() throws Exception {
        Resume testResume = new Resume("uuid4");
        storage.save(testResume);
        assertEquals(testResume, storage.get("uuid4"));
    }

    // StorageException
    @Test(expected = StorageException.class)
    public void StorageException() throws Exception {
        for (int i = storage.size(); i < STORAGE_LIMIT + 1; i++) {
            Resume testResume = new Resume();
            storage.save(testResume);
        }
        fail("Переполнение произошло раньше времени");
    }
}