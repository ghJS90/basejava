package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;
import java.util.stream.Collectors;

public class MapUuidStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();
    public static final Comparator<Resume> UUID_COMPARATOR = (Resume o1, Resume o2) -> o1.getUuid().compareTo(o2.getUuid());

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

    //    @Override
//    public List<Resume> getAllSorted() {
//        return new ArrayList<Resume>(storage.values());
//    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.values().stream()
                .sorted(UUID_COMPARATOR)
                .collect(Collectors.toList());
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
