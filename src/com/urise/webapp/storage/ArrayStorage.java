package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    /* @Override
    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("\nМассив заполнен. Резюме не сохранилось");
        } else if (findIndex(r.getUuid()) >= 0) {
            System.out.println("\nРезюме " + r.getUuid() + " уже присутствует в массиве.");
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    public boolean checkForExist(Resume r) {
        if (findIndex(r.getUuid()) >= 0) {
            System.out.println("\nРезюме " + r.getUuid() + " уже присутствует в массиве.");
            return true;
        } else {
            return false;
        }
    }*/

    @Override
    public void addResumeToStorage(Resume r) {
        if (findIndex(r.getUuid()) >= 0) {
            System.out.println("\nРезюме " + r.getUuid() + " уже присутствует в массиве.");
        } else {
            storage[size] = r;
            size++;
        }
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
