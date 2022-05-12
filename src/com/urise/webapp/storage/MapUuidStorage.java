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
        return storage.get(searchKey);
    }

    @Override
    public void saveResume(String searchKey, Resume r) {
        storage.put(searchKey, r);
    }

    @Override
    public void removeResume(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    public void updateResume(String searchKey, Resume r) {
        storage.replace(searchKey, r);
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
