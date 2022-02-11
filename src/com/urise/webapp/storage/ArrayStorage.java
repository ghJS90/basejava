package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    private int findResumeUuid(String uuid) {  // Метод для определения индекса резюме в массиве. Убираем дублируемый код.
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume r) {
        if (findResumeUuid(r.getUuid()) < 0) {
            System.out.println("\nРезюме " + r + " отсутствует в хранилище.");
        } else {
            storage[findResumeUuid(r.getUuid())] = r;
        }
    }

    public void clear() {
        Arrays.fill(storage, storage[size - 1]);
        size = 0;
    }

    public void save(Resume r) {
        if (size == 10000) {
            System.out.println("\nПостройте больше хранилищ!");
        } else if (findResumeUuid(r.getUuid()) >= 0) {
            System.out.println("\nРезюме " + r.getUuid() + " уже присутствует в хранилище.");
        } else {
            storage[size] = r;
            size++;
        }
    }


    public Resume get(String uuid) {
        if (findResumeUuid(uuid) >= 0) {
            return storage[findResumeUuid(uuid)];
        }
        System.out.println("\nРезюме " + uuid + " отсутствует в хранилище.");
        return null;
    }

    public void delete(String uuid) {
        if (findResumeUuid(uuid) >= 0) {
            System.arraycopy(storage, findResumeUuid(uuid) + 1, storage, findResumeUuid(uuid), size - findResumeUuid(uuid) - 1);
            size--;
        } else {
            System.out.println("\nРезюме " + uuid + " отсутствует в хранилище.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

}
