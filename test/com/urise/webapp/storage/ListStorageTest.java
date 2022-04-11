package com.urise.webapp.storage;

import org.junit.Test;

public class ListStorageTest extends AbstractArrayStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Test(expected = Test.None.class)
    public void StorageException() {
    }
}