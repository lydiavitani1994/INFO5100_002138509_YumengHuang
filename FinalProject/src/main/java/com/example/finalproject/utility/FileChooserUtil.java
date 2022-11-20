package com.example.finalproject.utility;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author YWu97
 */
public class FileChooserUtil {
    public static WritableImage getImageFromFile() throws IOException {
        FileChooser fileChooser = getImageFileChooser();
        File file = fileChooser.showOpenDialog(null);
        BufferedImage bufferedImage = ImageIO.read(file);
        return SwingFXUtils.toFXImage(bufferedImage, null);
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
}
