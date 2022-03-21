package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;
import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        storage.add(r);
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage.toArray(new Resume[0]), storage.size());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public int findIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if ((storage.get(i)).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Resume getByIndex(int index) {
        return storage.get(index);
    }

    @Override
    public void addResumeByIndex(Resume r, int i) {
        storage.set(i, r);
    }

    @Override
    public void removeByIndex(int index) {
       storage.remove(index);
    }
}
