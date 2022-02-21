package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("\nМассив заполнен. Резюме не сохранилось");
        } else if (size == 0) {
            storage[0] = r;
            size++;
        } else if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (r.getUuid().compareTo(storage[i].getUuid()) > 0) {
                    if (storage[i + 1] == null) {
                        storage[i + 1] = r;
                        size++;
                        break;
                    }
                } else if (r.getUuid().compareTo(storage[i].getUuid()) < 0) {
                    System.arraycopy(storage, i, storage, i + 1, size - i);
                    storage[i] = r;
                    size++;
                    break;
                } else if (r.getUuid().compareTo(storage[i].getUuid()) == 0) {
                    System.out.println("\nРезюме " + r.getUuid() + " уже присутствует в массиве.");
                    break;
                }
            }
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}

