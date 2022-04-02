package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public int findIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if ((storage.get(i)).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    public void updateResume(int i, Resume r) {
        storage.set(i, r);
    }

    @Override
    public void saveResume(int i, Resume r) {
        storage.add(r);
    }

    @Override
    public void removeResume(int index) {
        storage.remove(index);
    }
}
