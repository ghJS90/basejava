package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class FileStorage extends AbstractFileStorage {
    public FileStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume r, File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(r);
            System.out.println("Резюме " + r + " записано в файл.");
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doRead(File file) throws IOException {
        Resume resume = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            resume = (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        return resume;
    }
}
