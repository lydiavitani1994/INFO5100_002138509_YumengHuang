package com.example.finalproject.utility;

import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author YWu97
 */
public class FileChooserUtil {
    public static File getFile() throws IOException {
        FileChooser fileChooser = getImageFileChooser();
        fileChooser.setTitle("Choose Image");
        File file = fileChooser.showOpenDialog(null);
        return file;
    }

    private static FileChooser getImageFileChooser() {
        FileChooser fileChooser = new javafx.stage.FileChooser();

        FileChooser.ExtensionFilter extFilterJpg = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.JPEG");
        FileChooser.ExtensionFilter extFilterPng = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterJpeg = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
        FileChooser.ExtensionFilter extFilterSvg = new FileChooser.ExtensionFilter("SVG files (*.svg)", "*.SVG");
        FileChooser.ExtensionFilter extFilterBmp = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.BMP");
        FileChooser.ExtensionFilter extFilterTif = new FileChooser.ExtensionFilter("TIF files (*.tif)", "*.TIF");
        FileChooser.ExtensionFilter extFilterTiff = new FileChooser.ExtensionFilter("TIFF files (*.tiff)", "*.TIFF");

        fileChooser.setTitle("Please choose your image.");
        fileChooser.getExtensionFilters().addAll(extFilterJpg, extFilterPng, extFilterJpeg, extFilterSvg, extFilterBmp, extFilterTif, extFilterTiff);

        return fileChooser;
    }

    public static File getSaveFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(null);
        return file;
    }

}
