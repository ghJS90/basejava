package com.urise.webapp.storage;

import com.urise.webapp.storage.SerializeStrategy.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamSerializer()));
    }
}
