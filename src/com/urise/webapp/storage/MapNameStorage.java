package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapNameStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();
    public static final Comparator<Resume> FULLNAME_COMPARATOR = (Resume o1, Resume o2) -> o1.getFullName().compareTo(o2.getFullName());

    @Override
    protected Object findKey(String fullName) {
        return fullName;
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
    public void updateResume(Object key, Resume r) {
        storage.replace((String) key, r);
    }

    @Override
    public boolean isExist(Object key) {
        return storage.containsKey((String) key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<Resume>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
