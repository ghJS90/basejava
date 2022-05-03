package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;
import java.util.stream.Collectors;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();
    public static final Comparator<Resume> UUID_COMPARATOR = (Resume o1, Resume o2) -> o1.getUuid().compareTo(o2.getUuid());

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
    public void updateResume(Object key, Resume r) {
        storage.replace((String) key, r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> toGetSortedList = new ArrayList<Resume>(storage.values());
        toGetSortedList.sort(UUID_COMPARATOR);
        return toGetSortedList;
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
