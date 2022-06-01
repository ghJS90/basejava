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

        String path = "../basejava";
        walk(path);
    }

    private static int space = 0;
    public static void walk(String path) {

        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) return;
        for (File f : list) {
            if (f.isDirectory()) {
                for (int i = 0; i < space; i++) {
                    System.out.print(" ");
                }
                space++;
                System.out.println("Directory: " + f.getAbsolutePath());
                walk(f.getAbsolutePath());
            } else {
                for (int i = -1; i <= space; i++) {
                    System.out.print(" ");
                }
                System.out.println("File:" + f.getAbsoluteFile());
            }
        }
        space--;
    }
}