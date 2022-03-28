package com.urise.webapp.storage;

import com.urise.webapp.excepcion.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

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

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void save(Resume r) {
        if (size == storage.length) {
            throw new StorageException("Массив заполнен", r.getUuid());
        }
        checkForExist(r);
        int index = findIndex(r.getUuid());
        saveToArray(r, index);
        size++;
    }

    @Override
    public Resume getResume(int index) {
        return storage[index];
    }

    @Override
    public void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
    }

    @Override
    public abstract void saveToArray(Resume r, int i);

    @Override
    protected abstract int findIndex(String uuid);
}
