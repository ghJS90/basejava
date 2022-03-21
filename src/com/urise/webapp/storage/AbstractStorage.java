package com.urise.webapp.storage;

import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        addResumeByIndex(r, index);
    }

    @Override
    public abstract void clear();

    @Override
    public abstract void save(Resume r);

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return getByIndex(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            removeByIndex(index);
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public abstract Resume[] getAll();

    @Override
    public abstract int size();

    protected abstract int findIndex(String uuid);

    public abstract Resume getByIndex(int index);

    public abstract void addResumeByIndex(Resume r, int i);

    public abstract void removeByIndex(int i);
}
