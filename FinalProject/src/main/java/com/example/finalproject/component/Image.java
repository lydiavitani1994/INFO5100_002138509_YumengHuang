package com.example.finalproject.component;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.example.finalproject.utility.FileChooserUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private static int identification = 0;
    private final int id;
    private final File file;
    private final javafx.scene.image.Image FXImage;
    private final ImageView thumbNail;
    private String imageName;
    private String imageSizeStr;
    private long imageSize;
    private BufferedImage inputBufferedImage;
    private BufferedImage outputBufferedImage;
    private WritableImage writableImage;
    private VBox imageProperties;
    private String inputWidthStr;
    private int inputWidth;
    private int outputWidth;
    private String inputHeightStr;
    private int inputHeight;
    private int outputHeight;
    private String inputFormat;
    private String outputFormat;

    public Image(File file) throws ImageProcessingException, IOException {
        identification++;
        this.id = identification;
        this.file = file;
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String tagValue = tag.getDescription();
                switch (tagName) {
                    case ("File Name") -> this.imageName = tagValue;
                    case ("Detected File Type Name") -> this.inputFormat = tagValue;
                    case ("File Size") -> {
                        this.imageSizeStr = tagValue;
                        this.imageSize = Long.parseLong(tagValue.split(" ")[0]);
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

        this.thumbNail = new ImageView();
        this.thumbNail.setFitHeight(100);
        this.thumbNail.setFitWidth(100);
        this.thumbNail.setImage(this.FXImage);

        this.imageProperties = new VBox();
        this.imageProperties.setPrefSize(350, 150);
        this.imageProperties.setSpacing(5);
        this.imageProperties.getChildren().add(new Label("Name: " + this.imageName));
        this.imageProperties.getChildren().add(new Label("Type: " + this.inputFormat));
        this.imageProperties.getChildren().add(new Label("Size: " + this.imageSizeStr));
        this.imageProperties.getChildren().add(new Label("Height: " + this.inputHeightStr));
        this.imageProperties.getChildren().add(new Label("Width: " + this.inputWidthStr));
    }

    public VBox getImageProperties() {
        return this.imageProperties;
    }

    public ImageView getThumbNail() {
        return this.thumbNail;
    }

    public int getId() {
        return this.id;
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

//        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
//
//        Thumbnails.of(inputBufferedImage)
//                .size(outputImageWidth, outputImageHeight)
//                .outputFormat(outputFormatName)
//                .outputQuality(0.99)
//                .toOutputStream(byteOutputStream);
//        byte[] data = byteOutputStream.toByteArray();
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
//        outputBufferedImage =  ImageIO.read(byteArrayInputStream);
    }

    public void downloadSingleImage() throws IOException {
        changeSize();
        File writeFile = FileChooserUtil.getSaveFile(this.outputFormat);
        if (writeFile != null) {
            ImageIO.write(this.outputBufferedImage, this.outputFormat, writeFile);
        }
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
        return this.id + "." + this.outputFormat.toLowerCase();
    }

}
