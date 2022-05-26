package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = findExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = findExistedSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = findNotExistedSearchKey(r.getUuid());
        doSave(searchKey, r);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = findExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> list = getList();
        Collections.sort(list);
        return list;
    }

    private SK findNotExistedSearchKey(String uuid) {
        SK searchKey = searchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Резюме " + uuid + " уже находится в массиве");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK findExistedSearchKey(String uuid) {
        SK searchKey = searchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Резюме " + uuid + " отсутствует в массиве");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract List<Resume> getList();

    protected abstract SK searchKey(String uuid);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doSave(SK searchKey, Resume r);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doUpdate(SK searchKey, Resume r);

    protected abstract boolean isExist(SK searchKey);
}
