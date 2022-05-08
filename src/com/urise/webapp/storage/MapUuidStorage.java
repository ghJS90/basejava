package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public List<Resume> getList() {
        return new ArrayList<Resume>(storage.values());
    }

    @Override
    protected Object searchKey(String uuid) {
        return uuid;
    }

    @Override
    public Resume getResume(Object key) {
        return storage.get((String) key);
    }

    @Override
    public void saveResume(Object key, Resume r) {
        storage.put((String) key, r);
    }

    @Override
    public void removeResume(Object key) {
        storage.remove((String) key);
    }

    @Override
    public void updateResume(Object searchKey, Resume r) {
        storage.replace((String) searchKey, r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isExist(Object key) {
        return storage.containsKey((String) key);
    }
}
