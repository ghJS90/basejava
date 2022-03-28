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
        saveToArray(r, index);
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

    public void checkForExist(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
    }

    protected abstract int findIndex(String uuid);

    public abstract Resume getResume(int index);

    public abstract void saveToArray(Resume r, int i);

    public abstract void removeResume(int i);

}
