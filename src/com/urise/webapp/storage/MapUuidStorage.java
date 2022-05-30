package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Resume doGet(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    public void doSave(String searchKey, Resume r) {
        storage.put(searchKey, r);
    }

    @Override
    public void doDelete(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    public void doUpdate(String searchKey, Resume r) {
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
