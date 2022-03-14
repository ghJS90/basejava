package com.urise.webapp.excepcion;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String uuid) {
        super("Резюме " + uuid + " отсутствует в массиве", uuid);
    }
}
