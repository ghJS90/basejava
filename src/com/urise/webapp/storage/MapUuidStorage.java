package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public List<Resume> getList() {
        return new ArrayList<Resume>(storage.values());
    }

    @Override
    protected String searchKey(String uuid) {
        return uuid;
    }

    @Override
    public Resume getResume(String searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    public void saveResume(String searchKey, Resume r) {
        storage.put((String) searchKey, r);
    }

    @Override
    public void removeResume(String searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    public void updateResume(String searchKey, Resume r) {
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
    public boolean isExist(String searchKey) {
        return storage.containsKey((String) searchKey);
    }
}
