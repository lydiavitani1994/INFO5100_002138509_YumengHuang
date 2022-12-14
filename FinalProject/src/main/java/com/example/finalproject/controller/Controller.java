package com.example.finalproject.controller;

import com.drew.imaging.ImageProcessingException;
import com.example.finalproject.component.Image;
import com.example.finalproject.component.ImageDaoImpl;
import com.example.finalproject.component.ImagePanel;
import com.example.finalproject.utility.FileChooserUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private final List<String> formatList = Arrays.asList("JPG", "JPEG", "PNG", "BMP", "TIF", "TIFF");
    private final List<String> sizeList = Arrays.asList("Original Size", "100px wide * 100px high", "360px wide * 360px high", "820px wide * 312px high");
    @FXML
    public VBox imageViewContainer;
    public ScrollPane imageViewScrollPane;
    ImageDaoImpl imageList = new ImageDaoImpl();
    private List<File> files;
    @FXML
    private VBox uploadDialog;

    public void addImageAction() throws IOException, ImageProcessingException {
        // Add image from file and add into imageList
        files = FileChooserUtil.getFiles();
        List<Image> addedImages = imageList.addMultipleImages(files);

        // Create UI components for image files
        if (imageList.getNumberOfImages() > 0) {
            // Visibility setting of relative components
            imageViewScrollPane.setVisible(true);
            uploadDialog.setVisible(false);
        }

        for (Image image : addedImages) {
            // Create panel for each image file
            ImagePanel imagePanel = new ImagePanel(image);

            imagePanel.getDeleteBtn().setOnAction(event -> {
                // Get the parent node and its fx:id
                String IdToDelete = imagePanel.getPanel().getId();
                System.out.println(Integer.parseInt(IdToDelete));
                // Download respective image in the imageList with fx:id
                imageList.deleteSingleImage(Integer.parseInt(IdToDelete));
                imageViewContainer.getChildren().remove(imagePanel.getPanel());
            });

            imagePanel.getDownloadBtn().setOnAction(event -> {
                try {
                    // Get the parent node and its fx:id
                    String IdToDownload = imagePanel.getPanel().getId();
                    System.out.println(Integer.parseInt(IdToDownload));
                    // Download respective image in the imageList with fx:id
                    imageList.downloadSingleImage(Integer.parseInt(IdToDownload));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            imageViewContainer.getChildren().add(imagePanel.getPanel());
        }

    }

    // image format & size converter
    public void downloadAllAction() throws IOException {
        imageList.downloadAllImages();
    }

    public void deleteAllImagesAction() {
        // Delete items in imageList
        imageList.deleteAllImages();
        // Delete panels in imageViewContainer
        imageViewContainer.getChildren().clear();
        imageViewScrollPane.setVisible(false);
        uploadDialog.setVisible(true);
    }
}