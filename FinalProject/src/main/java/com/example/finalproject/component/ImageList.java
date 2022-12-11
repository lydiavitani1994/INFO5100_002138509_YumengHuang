package com.example.finalproject.component;

import com.drew.imaging.ImageProcessingException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageList {
    private List<Image> imageList;

    public ImageList() {
        imageList = new ArrayList<>();
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public Integer getNumberOfImages() {
        return imageList.size();
    }

    public void addImage(Image image) {
        this.imageList.add(image);
    }

    public void addMultipleImages(List<File> files) throws ImageProcessingException, IOException {
        for (File file : files) {
            Image image = new Image(file);
            addImage(image);
        }
    }

    public void deleteImage(Integer targetId) {
        imageList.removeIf(value -> value.getId() == targetId);
    }

    public void deleteAllImages() {
        imageList.clear();
    }
}
