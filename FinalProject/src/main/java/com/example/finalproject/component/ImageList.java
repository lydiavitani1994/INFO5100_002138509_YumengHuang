package com.example.finalproject.component;

import com.drew.imaging.ImageProcessingException;
import com.example.finalproject.utility.FileChooserUtil;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ImageList {
    private List<Image> imageList;

    public ImageList() {
        imageList = new ArrayList<>();
    }

    public Integer getNumberOfImages() {
        return imageList.size();
    }

    public void addImage(Image image) {
        this.imageList.add(image);
    }

    /*
    return new added images
    imageList is all active images
     */
    public List<Image> addMultipleImages(List<File> files) throws ImageProcessingException, IOException {
        List<Image> newList = new ArrayList<>();
        for (File file : files) {
            Image image = new Image(file);
            addImage(image);
            newList.add(image);
        }
        return newList;
    }

    public void deleteImage(Integer targetId) {
        imageList.removeIf(value -> value.getId() == targetId);
    }

    public void deleteAllImages() {
        imageList.clear();
    }

    public void downloadAllImages() throws IOException {
        File writeZipFile = FileChooserUtil.getSaveFile("ZIP");
        if (writeZipFile != null){
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(writeZipFile));
            FileOutputStream fos = new FileOutputStream(writeZipFile);

            for(Image image: this.imageList){
                image.changeSize();
                File outputImageFile = new File(image.getZipName());
                ImageIO.write(image.getOutputBufferedImage(), image.getOutputFormat(), outputImageFile);

                FileInputStream fis = new FileInputStream(outputImageFile);
                ZipEntry zipEntry = new ZipEntry(outputImageFile.getName());
                zipOutputStream.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes, 0, length);
                }
                fis.close();

            }
            zipOutputStream.close();
            fos.close();
        }
    }
}
