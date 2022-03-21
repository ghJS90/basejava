package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;
import com.urise.webapp.excepcion.NotExistStorageException;
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

    public void save(Resume r) {
        String uuid = r.getUuid();
        if (size == storage.length) {
            throw new StorageException("Массив заполнен", uuid);
        }
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        addResumeByIndex(r, index);
        size++;
    }

    @Override
    public Resume getByIndex(int index) {
        return storage[index];
    }

    @Override
    public void removeByIndex(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
    }

    @Override
    public abstract void addResumeByIndex(Resume r, int i);

    @Override
    protected abstract int findIndex(String uuid);
}
