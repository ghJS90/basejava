package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
        File filePath = new File(".\\.gitignore\"");
        System.out.println(filePath.getCanonicalPath());
        File dir = new File(".\\src\\com\\urise\\webapp");
        System.out.println(dir.isDirectory());
        dir.list();
        String[] list = dir.list();
        if (list != null) {
            for (String name : dir.list()) {
                System.out.println(name);
            }
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            System.out.println(fis.read());
        } catch (IOException e){
            throw new RuntimeException(e);
        } finally {
            if (fis != null){
                fis.close();
            }
        }

    }
}
