package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;
import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void update(Resume r) {
        delete(r.getUuid());
        storage.add(r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume r) {
        if (storage.contains(r)){
            throw new ExistStorageException(r.getUuid());
        }
        storage.add(r);
    }

    @Override
    public Resume get(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return r;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                storage.remove(r);
                return;
            }
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage.toArray(new Resume[0]), storage.size());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
