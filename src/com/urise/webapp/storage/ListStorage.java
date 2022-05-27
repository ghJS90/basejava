package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public List<Resume> getList() {
        return storage;
    }

    @Override
    public Integer searchKey(String uuid) {
        for (int key = 0; key < storage.size(); key++) {
            if (storage.get(key).getUuid().equals(uuid)) {
                return key;
            }
        }
        return -1;
    }

    @Override
    public Resume doGet(Integer key) {
        return storage.get(key);
    }

    @Override
    public void doUpdate(Integer key, Resume r) {
        storage.set( key, r);
    }

    @Override
    public boolean isExist(Integer key) {
        return key >= 0;
    }

    @Override
    public void doSave(Integer key, Resume r) {
        storage.add(r);
    }

    @Override
    public void doDelete(Integer key) {
        storage.remove((int) key);
    }
}
