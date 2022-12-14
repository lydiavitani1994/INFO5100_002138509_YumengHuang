package com.example.finalproject.component;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends MediaFile {
    private final javafx.scene.image.Image FXImage;
    private final BufferedImage inputBufferedImage;
    private String imageName;
    private String model;
    private String imageSize;
    private String longitude;
    private String latitude;
    private BufferedImage outputBufferedImage;
    private String inputWidthStr;
    private int inputWidth;
    private int outputWidth;
    private String inputHeightStr;
    private int inputHeight;
    private int outputHeight;
    private String inputFormat;
    private String outputFormat;

    public Image(File file) throws ImageProcessingException, IOException {
        super(file);

        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String tagValue = tag.getDescription();
                switch (tagName) {
                    case ("File Name") -> this.imageName = tagValue;
                    case ("Detected File Type Name") -> this.inputFormat = tagValue;
                    case ("File Size") -> this.imageSize = tagValue;
                    case ("Model") -> {
                        this.model = tagValue;
                    }
                    case ("GPS Longitude") -> {
                        this.longitude = tagValue;
                    }
                    case ("GPS Latitude") -> {
                        this.latitude = tagValue;
                    }
                    case ("Image Height") -> {
                        this.inputHeightStr = tagValue;
                        this.inputHeight = Integer.parseInt(tagValue.split(" ")[0]);
                    }
                    case ("Image Width") -> {
                        this.inputWidthStr = tagValue;
                        this.inputWidth = Integer.parseInt(tagValue.split(" ")[0]);
                    }
                    default -> {
                    }
                }
            }
        }
        this.inputBufferedImage = ImageIO.read(file);
        this.FXImage = SwingFXUtils.toFXImage(inputBufferedImage, null);
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getModel() {
        return model;
    }

    public javafx.scene.image.Image getFXImage() {
        return FXImage;
    }

    @Override
    public String getName() {
        return imageName;
    }

    public String getImageSize() {
        return imageSize;
    }

    public String getInputWidthStr() {
        return inputWidthStr;
    }

    public String getInputHeightStr() {
        return inputHeightStr;
    }

    public String getInputFormat() {
        return this.inputFormat;
    }

    public void setOutputSizeOriginal() {
        this.outputHeight = this.inputHeight;
        this.outputWidth = this.inputWidth;
    }

    public void setOutputSize(int outputWidth, int outputHeight) {
        this.outputWidth = outputWidth;
        this.outputHeight = outputHeight;
    }

    public void changeSize() throws IOException {
        // creates output image
        this.outputBufferedImage = new BufferedImage(this.outputWidth, this.outputHeight, this.inputBufferedImage.getType());
        // scales the input image to the output image
        Graphics2D g2d = this.outputBufferedImage.createGraphics();
        g2d.drawImage(this.inputBufferedImage, 0, 0, this.outputWidth, this.outputHeight, null);
        g2d.dispose();
    }

    public BufferedImage getOutputBufferedImage() {
        return this.outputBufferedImage;
    }

    public String getOutputFormat() {
        return this.outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getZipName() {
        return getId() + "." + this.outputFormat.toLowerCase();
    }
}