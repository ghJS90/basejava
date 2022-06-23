package com.urise.webapp.storage;

import com.urise.webapp.storage.SerializeStrategy.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest{
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new DataStreamSerializer()));
    }
}
