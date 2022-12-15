package com.example.finalproject.component;

import java.io.File;

public class MediaFile {
    private static int identification = 0;
    private final int id;
    private final File file;
    private final String fileName;

    public MediaFile(File file) {
        identification++;
        this.id = identification;
        this.file = file;
        this.fileName = file.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return fileName;
    }
}
