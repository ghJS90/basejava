package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SerializeStrategy.SerializeStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    SerializeStrategy strategy;

    public PathStorage(String dir) {
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory/writeable");
        }
        this.directory = Paths.get(dir);
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> result = new ArrayList<>();
        try (Stream<Path> streamPath = Files.list(directory)) {
            streamPath.forEach(path -> result.add(doGet(path)));
        } catch (IOException e) {
            throw new StorageException("IO error", null, e);
        }
        return result;
    }

    @Override
    protected Path searchKey(String uuid) {
        return Paths.get(directory.toString(), uuid);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toFile().getName(), e);
        }
    }

    @Override
    protected void doSave(Path path, Resume r) {
        try {
            Files.createFile(Objects.requireNonNull(path));
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + path.toFile().getName(), path.toFile().getName(), e);
        }
        doUpdate(path, r);
    }

    @Override
    protected void doDelete(Path path) {
        if (path.toFile().delete()) {
            System.out.println("Path " + path + " deleted");
        } else {
            throw new StorageException("Path delete error", path.toFile().getName());
        }
    }

    @Override
    protected void doUpdate(Path path, Resume r) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toFile().getName(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return path.toFile().exists();
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(PathStorage.this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error ", null);
        }
    }

    @Override
    public int size() {
        String[] list = directory.toFile().list();
        if (list == null) {
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
