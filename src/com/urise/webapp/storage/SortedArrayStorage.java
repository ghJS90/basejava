package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    /* @Override
    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("\nМассив заполнен. Резюме не сохранилось");
            return;
        }
        int index = -(findIndex(r.getUuid())) - 1;
        if (index < 0) {
            System.out.println("\nРезюме " + r.getUuid() + " уже присутствует в массиве.");
        } else {
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        }
    }

    @Override
    public boolean checkForExist(Resume r) {
        int index = -(findIndex(r.getUuid())) - 1;
        if (index < 0) {
            System.out.println("\nРезюме " + r.getUuid() + " уже присутствует в массиве.");
            return true;
        } else {
            return false;
        }
    }*/

    @Override
    public void addResumeToStorage(Resume r) {
        int index = -(findIndex(r.getUuid())) - 1;
        if (index < 0) {
            System.out.println("\nРезюме " + r.getUuid() + " уже присутствует в массиве.");
        } else {
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

