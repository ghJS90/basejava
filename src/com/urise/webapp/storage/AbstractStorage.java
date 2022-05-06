package com.urise.webapp.storage;

import com.urise.webapp.excepcion.ExistStorageException;
import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    public static final Comparator<Resume> UUID_COMPARATOR = (Resume o1, Resume o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    public void delete(String uuid) {
        Object searchKey = findExistedSearchKey(uuid);
        removeResume(searchKey);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = findExistedSearchKey(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = findNotExistedSearchKey(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = findExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> test = getList();
        test.sort(UUID_COMPARATOR);
        return test;
    }

    private Object findNotExistedSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object findExistedSearchKey(String uuid) {
        Object searchKey = searchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public abstract List<Resume> getList();

    protected abstract Object searchKey(String uuid);

    public abstract Resume getResume(Object key);

    public abstract void saveResume(Object key, Resume r);

    public abstract void removeResume(Object key);

    public abstract void updateResume(Object key, Resume r);

    public abstract boolean isExist(Object key);

}
