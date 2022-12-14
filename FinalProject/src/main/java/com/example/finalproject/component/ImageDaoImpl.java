package com.example.finalproject.component;

import com.drew.imaging.ImageProcessingException;
import com.example.finalproject.utility.FileChooserUtil;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ImageDaoImpl implements ImageDao {
    private List<Image> imageList;

    public ImageDaoImpl() {
        imageList = new ArrayList<>();
    }

    public Integer getNumberOfImages() {
        return imageList.size();
    }

    public void addSingleImage(Image image) {
        this.imageList.add(image);
    }

    /*
    return new added images
    imageList is all active images
     */
    public List<Image> addMultipleImages(List<File> files) throws ImageProcessingException, IOException {
        List<Image> newList = new ArrayList<>();
        if(files != null){
            for (File file : files) {
                Image image = new Image(file);
                addSingleImage(image);
                newList.add(image);
            }
        }
        return newList;
    }

    public void deleteSingleImage(Integer targetId) {
        imageList.removeIf(value -> value.getId() == targetId);
    }

    public void deleteAllImages() {
        imageList.clear();
    }

    public void downloadSingleImage(Integer targetId) throws IOException {
        Image targetImage = null;

        for (Image image : imageList) {
            if (image.getId() == targetId) {
                targetImage = image;
            }
        }

        if (targetImage != null) {
            targetImage.changeSize();
            File writeFile = FileChooserUtil.getSaveFile(targetImage.getOutputFormat());
            if (writeFile != null) {
                ImageIO.write(targetImage.getOutputBufferedImage(), targetImage.getOutputFormat(), writeFile);
            }
        } else {
            System.out.println("no target");
        }
    }

    public void downloadAllImages() throws IOException {
        File writeZipFile = FileChooserUtil.getSaveFile("ZIP");
        if (writeZipFile != null) {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(writeZipFile));
            FileOutputStream fos = new FileOutputStream(writeZipFile);

            for (Image image : imageList) {
                image.changeSize();
                File outputImageFile = new File(image.getZipName());
                ImageIO.write(image.getOutputBufferedImage(), image.getOutputFormat(), outputImageFile);

                FileInputStream fis = new FileInputStream(outputImageFile);
                ZipEntry zipEntry = new ZipEntry(outputImageFile.getName());
                zipOutputStream.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes, 0, length);
                }
                fis.close();

            }
            zipOutputStream.close();
            fos.close();
        }
    }
}
