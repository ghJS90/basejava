package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private final static Storage ARRAY_STORAGE = new SqlStorage("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1", "NAME_ONE");
        Resume r2 = new Resume("uuid2", "B");
        Resume r3 = new Resume("uuid3", "C");

        ARRAY_STORAGE.clear();

        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());
        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        Resume r7 = new Resume("uuid3", "A");
        ARRAY_STORAGE.update(r7);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());

        printAll();
        ARRAY_STORAGE.clear();

        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
