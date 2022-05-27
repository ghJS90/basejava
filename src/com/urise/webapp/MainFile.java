package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String path = "C:\\BASEJAVA\\basejava";
        int countFiles = 0;
        int countDir = 0;
        walk(path);
        System.out.println( + countDir + "\nFiles: " + countFiles);
    }

    public static void walk(String path) {

        File root = new File(path);
        File[] list = root.listFiles();
        int countFiles = 0;
        int countDir = 0;
        if (list == null) return;

        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
                System.out.println("Dir:" + f.getAbsoluteFile());
                countDir++;
            } else {
                System.out.println("File:" + f.getAbsoluteFile());
                countFiles++;
            }
        }

    }
}