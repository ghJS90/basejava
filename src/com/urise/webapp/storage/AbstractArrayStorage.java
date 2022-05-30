package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    @Override
    public List<Resume> getList() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    public void doDelete(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
    }

    @Override
    public void doUpdate(Integer index, Resume r) {
        storage[index] = r;
    }

    @Override
    public void doSave(Integer index, Resume r) {
        if (size == storage.length) {
            throw new StorageException("Массив заполнен", r.getUuid());
        }
        addResume(index, r);
        size++;
    }

    @Override
    public boolean isExist(Integer index) {
        return index >= 0;
    }

    public abstract void addResume(int i, Resume r);
}
