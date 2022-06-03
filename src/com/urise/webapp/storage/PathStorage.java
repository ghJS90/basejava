package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SerializeStrategy.StreamStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamStrategy strategy = new StreamStrategy();

    public PathStorage(String dir) {
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory/writeable");
        }
    }

    protected Stream<Path> doReadAll() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("IO error", null, e);
        }
    }

    @Override
    protected List<Resume> getList() {
        List<Resume> result = new ArrayList<>();
        doReadAll().forEach(path -> result.add(doGet(path)));
        return result;
    }

    @Override
    protected Path searchKey(String uuid) {
        return directory.resolve(uuid);
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
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error ", path.toString());
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
        doReadAll().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) doReadAll().count();
    }
}










