package com.urise.webapp.storage;

import com.urise.webapp.excepcion.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected int findIndex(String uuid) {
        int index = 0;
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (uuid.equals(entry.getValue().getUuid())) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public Resume getResume(int index) {
        int i = 0;
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (i == index) {
                return entry.getValue();
            }
            i++;
        }
        return null;
    }

    @Override
    public void saveResume(int i, Resume r) {
        String key = r.getUuid();
        storage.put(key, r);
    }

    @Override
    public void removeResume(int i) {
        Resume result = getResume(i);
        storage.remove(result.getUuid());
    }

    @Override
    public void updateResume(int i, Resume r) {
        Resume oldResume = getResume(i);
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
}
