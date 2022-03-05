package com.urise.webapp.storage;

import com.urise.webapp.excepcion.NotExistStorageExeption;
import com.urise.webapp.excepcion.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    Storage storage;

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

    @Test
    public void update() throws NotExistStorageExeption {
        Resume testResume = new Resume("uuid3");
        storage.update(testResume);
        Assert.assertSame(testResume, storage.get("uuid3"));
    }

    @Test(expected = NotExistStorageExeption.class)
    public void clear() throws Exception {
        storage.clear();
        Assert.assertNull(storage.get(UUID_1));
        Assert.assertNull(storage.get(UUID_2));
        Assert.assertNull(storage.get(UUID_3));
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageExeption.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertNull(storage.get(UUID_1));
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception {
        Resume testResume = new Resume(UUID_1);
        Assert.assertEquals(storage.get(UUID_1), testResume);
    }

    @Test
    public void save() throws Exception {
        Resume testResume = new Resume("uuid4");
        storage.save(testResume);
        Assert.assertSame(testResume, storage.get("uuid4"));
    }

    @Test (expected = StorageException.class)
    public void StorageException() throws Exception {
        Resume testResumeOne = new Resume("uuid4");
        Resume testResumeTwo = new Resume("uuid5");
        storage.save(testResumeOne);
        storage.save(testResumeTwo);
        Assert.fail("Переполнение произошло раньше времени");
    }
}