package com.urise.webapp.excepcion;

public class NotExistStorageExeption extends StorageException{
    public NotExistStorageExeption(String uuid) {
        super("Резюме " + uuid + " отсутствует в массиве", uuid);
    }
}
