package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Object findKey(String uuid) {
        for (int key = 0; key < storage.size(); key++) {
            if (storage.get(key).getUuid().equals(uuid)) {
                return key;
            }
        }
        return -1;
    }

    @Override
    public Resume getResume(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    public void updateResume(Object key, Resume r) {
        storage.set((Integer) key, r);
    }

    @Override
    public boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    @Override
    public void saveResume(Object key, Resume r) {
        storage.add(r);
    }

    @Override
    public void removeResume(Object key) {
        storage.remove((int) key);
    }
}
