package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object findKey(String uuid) {
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
        Resume result = getResume(key);
        storage.remove(result.getUuid());
    }

    @Override
    public void updateResume(Object key, Resume r) {
        Resume oldResume = getResume(key);
        storage.replace(oldResume.getUuid(), r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        int size = storage.size();
        Resume[] result = new Resume[size];
        int i = 0;
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            result[i++] = entry.getValue();
        }
        return result;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public boolean isExist(Object key) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if ((key.toString()).equals(entry.getValue().getUuid())) {
                return true;
            }
        }
        return false;
    }
}
