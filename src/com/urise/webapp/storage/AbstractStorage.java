package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;
import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateResume(index, r);
    }

    @Override
    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
        saveResume(index, r);
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        removeResume(index);
    }

    protected abstract int findIndex(String uuid);

    public abstract Resume getResume(int index);

    public abstract void saveResume(int i, Resume r);

    public abstract void removeResume(int i);

    public abstract void updateResume(int i, Resume r);

}
