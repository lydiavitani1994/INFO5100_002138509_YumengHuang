package com.example.finalproject.controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import com.example.finalproject.utility.FileChooserUtil;
import com.example.finalproject.utility.ImageUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Controller {
    @FXML
    public Label alert;
    public HBox imageViewContainer;
    public VBox imageProperties;
    @FXML
    private ToggleGroup format;
    @FXML
    private ToggleGroup size;
    @FXML
    private VBox uploadDialog;
    @FXML
    private ImageView imageView;
    private WritableImage image;

    private int inputImageWidth;
    private int inputImageHeight;
    private int outputImageWidth;
    private int outputImageHeight;
    private String inputFormatName;
    private String outputFormatName;
    private BufferedImage inputBufferedImage;

    private BufferedImage outputBufferedImage;

    public void openImageAction(ActionEvent actionEvent) throws IOException, ImageProcessingException {
        // Choose image from file
        File file = FileChooserUtil.getFile();
        inputBufferedImage = ImageIO.read(file);
        inputImageWidth = inputBufferedImage.getWidth();
        inputImageHeight = inputBufferedImage.getHeight();
        long imageSize = file.length();
        ImageInputStream iis = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
        if (! iter.hasNext()) {
            throw new RuntimeException("No readers found!");
        }
        ImageReader reader = iter.next();
        inputFormatName = reader.getFormatName();

        System.out.println("image_width"+inputImageWidth);
        System.out.println("image_height"+inputImageHeight);
        System.out.println("image_size"+ imageSize);
        System.out.println("image_format"+inputFormatName);

        image = SwingFXUtils.toFXImage(inputBufferedImage, null);

        // Get image properties and display in window
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String description = tag.getDescription();
                imageProperties.getChildren().add(new Label(tagName + ": " + description));
            }
        }

        // Resize image to fit window
        ImageUtil.fitImage(image, imageViewContainer, imageView);

        // Display image in window
        imageView.setImage(image);

        // Change visibility of relative components
        imageView.setVisible(true);
        uploadDialog.setVisible(false);
    }

    // image format & size converter
    public void navDownloadAction(ActionEvent actionEvent) throws IOException {
        // format
        RadioButton imageFormatButton = (RadioButton) format.getSelectedToggle();
        if(imageFormatButton == null){
            outputFormatName = inputFormatName;
        }else{
            outputFormatName = imageFormatButton.getText();
            if (outputFormatName.equals("original format")){
                outputFormatName = inputFormatName;
            }
        }
        // size
        RadioButton imageSizeButton = (RadioButton) size.getSelectedToggle();
        String sizeValue = "original size";
        if(imageSizeButton != null){
            sizeValue = imageSizeButton.getText();
        }
        if (sizeValue.equals("original size")) {
            outputImageWidth = inputImageWidth;
            outputImageHeight = inputImageHeight;
        } else if (sizeValue.equals("100px wide * 100px high")) {
            outputImageWidth = 100;
            outputImageHeight = 100;
        } else if (sizeValue.equals("360px wide * 360px high")) {
            outputImageWidth = 360;
            outputImageHeight = 360;
        } else if (sizeValue.equals("820px wide * 312px high")) {
            outputImageWidth = 820;
            outputImageHeight = 312;
        } else {
            outputImageWidth = inputImageWidth;
            outputImageHeight = inputImageHeight;
        }
//        Image image = bufferedImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//        BufferedImage scaledBI = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        changeSize();
        // download
        File writeFile = FileChooserUtil.getSaveFile(outputFormatName);
        ImageIO.write(outputBufferedImage, outputFormatName, writeFile);
    }

    private void changeSize() throws IOException {
        // creates output image
        outputBufferedImage = new BufferedImage(outputImageWidth, outputImageHeight, inputBufferedImage.getType());
        // scales the input image to the output image
        Graphics2D g2d = outputBufferedImage.createGraphics();
        g2d.drawImage(inputBufferedImage, 0, 0, outputImageWidth, outputImageHeight, null);
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

//    // TODO: image zoom in / out, reset (TBD)
//    public void zoomIn(ActionEvent actionEvent) {
//    }
//
//    public void reset(ActionEvent actionEvent) {
//    }
//
//    public void zoomOut(ActionEvent actionEvent) {
//    }
}