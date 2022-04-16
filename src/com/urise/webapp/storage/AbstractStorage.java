package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;
import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object searchKey = checkForNotExist(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = checkForExist(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = checkForNotExist(uuid);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = checkForNotExist(uuid);
        removeResume(searchKey);
    }

    public Object checkForExist(String uuid) {
        Object searchKey = findKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public Object checkForNotExist(String uuid) {
        Object searchKey = findKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object findKey(String uuid);

    public abstract Resume getResume(Object key);

    public abstract void saveResume(Object key, Resume r);

    public abstract void removeResume(Object key);

    public abstract void updateResume(Object key, Resume r);

    public abstract boolean isExist(Object key);

}
