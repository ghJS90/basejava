package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public List<Resume> getList() {
        return new ArrayList<>(storage.values());
    }

    protected Resume searchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public Resume getResume(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    public void saveResume(Object key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void removeResume(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    public void updateResume(Object searchKey, Resume r) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    public boolean isExist(Object searchKey) {
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
