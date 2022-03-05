package com.urise.webapp.excepcion;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Резюме " + uuid + "уже нахоится в массиве", uuid);
    }
}
