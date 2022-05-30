package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(new File("C:/testFileStorage")));
    }

    @Test
    @Override
    public void update() throws Exception {
        Resume testResume = new Resume("uuid3", "F");
        storage.update(testResume);
        assertEquals(testResume, storage.get("uuid3"));
    }
}
