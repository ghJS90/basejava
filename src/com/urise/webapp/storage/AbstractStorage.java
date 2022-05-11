package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {


    @Override
    public void delete(String uuid) {
        SK searchKey = findExistedSearchKey(uuid);
        removeResume(searchKey);
    }

    @Override
    public void update(Resume r) {
        SK searchKey = findExistedSearchKey(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        SK searchKey = findNotExistedSearchKey(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = findExistedSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getList();
        Collections.sort(list);
        return list;
    }

    private SK findNotExistedSearchKey(String uuid) {
        SK searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK findExistedSearchKey(String uuid) {
        SK searchKey = searchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public abstract List<Resume> getList();

    protected abstract SK searchKey(String uuid);

    public abstract Resume getResume(SK searchKey);

    public abstract void saveResume(SK searchKey, Resume r);

    public abstract void removeResume(SK searchKey);

    public abstract void updateResume(SK searchKey, Resume r);

    public abstract boolean isExist(SK searchKey);

}
