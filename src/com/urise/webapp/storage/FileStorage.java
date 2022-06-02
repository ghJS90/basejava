package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SerializeStrategy.SerializeStrategy;
import com.urise.webapp.storage.SerializeStrategy.StreamStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    StreamStrategy strategy = new StreamStrategy();

    protected FileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writeable");
        }
        this.directory = directory;
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> result = new ArrayList<>();
        for (File f : Objects.requireNonNull(directory.listFiles())) {
            try {
                result.add(strategy.doRead(new BufferedInputStream(new FileInputStream(f))));
            } catch (IOException e) {
                throw new StorageException("IO error", null, e);
            }
        }
        return result;
    }

    @Override
    protected File searchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(file, r);
    }

    @Override
    protected void doDelete(File file) {
        if (file.delete()) {
            System.out.println("File " + file + " deleted");
        } else {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public void clear() {
        for (File f : Objects.requireNonNull(directory.listFiles())) {
            doDelete(f);
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null){
            throw new StorageException("Directory read error ", null);
        }
        return list.length;
    }

//    protected void doWrite(Resume r, OutputStream os) throws IOException{
//        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
//            oos.writeObject(r);
//        }
//    }
//
//    protected Resume doRead(InputStream is) throws IOException {
//        try (ObjectInputStream ois = new ObjectInputStream(is)) {
//            return (Resume) ois.readObject();
//        } catch (ClassNotFoundException e) {
//            throw new StorageException("Error read resume", null, e);
//        }
//    }
}
