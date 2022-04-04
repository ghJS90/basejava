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
    public Resume getResume(int index) {
        return storage[index];
    }

    @Override
    public void removeResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
    }

    public void expandSize(Resume r) {
        if (size == storage.length) {
            throw new StorageException("Массив заполнен", r.getUuid());
        }
        size++;
    }

    @Override
    public void updateResume(int index, Resume r) {
        storage[index] = r;
    }

    @Override
    public abstract void saveResume(int i, Resume r);

    @Override
    protected abstract int findIndex(String uuid);

}
