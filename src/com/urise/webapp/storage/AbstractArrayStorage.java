package com.urise.webapp.storage;

import com.urise.webapp.excepcion.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public List<Resume> getAllSorted() {
        return Arrays.asList(storage);
    }

    @Override
    public Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    @Override
    public void removeResume(Object index) {
        System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, size - (Integer) index - 1);
        size--;
    }

    @Override
    public void updateResume(Object index, Resume r) {
        storage[(Integer) index] = r;
    }

    @Override
    public void saveResume(Object index, Resume r) {
        if (size == storage.length) {
            throw new StorageException("Массив заполнен", r.getUuid());
        }
        addResume((Integer) index, r);
        size++;
    }

    @Override
    public boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public abstract void addResume(int i, Resume r);
}
