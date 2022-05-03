package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;
import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void delete(String uuid) {
        Object searchKey = findNotExistedSearchKey(uuid);
        removeResume(searchKey);
    }

    private Object findExistedSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object findNotExistedSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public void update(Resume r) {
        Object searchKey = findNotExistedSearchKey(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = findExistedSearchKey(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = findNotExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    protected abstract Object searchKey(String uuid);

    public abstract Resume getResume(Object key);

    public abstract void saveResume(Object key, Resume r);

    public abstract void removeResume(Object key);

    public abstract void updateResume(Object key, Resume r);

    public abstract boolean isExist(Object key);

}
