package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public List<Resume> getList() {
        return new ArrayList<>(storage.values());
    }

    protected Resume searchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public Resume doGet(Resume searchKey) {
        return searchKey;
    }

    @Override
    public void doSave(Resume key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void doDelete(Resume searchKey) {
        storage.remove((searchKey).getUuid());
    }

    @Override
    public void doUpdate(Resume searchKey, Resume r) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    public boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
