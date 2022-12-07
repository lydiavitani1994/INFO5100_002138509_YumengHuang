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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    public Label alert;
    public HBox imageViewContainer;
    public VBox imageProperties;
    @FXML
    private ToggleGroup format;
    @FXML
    private ToggleGroup filter;
    @FXML
    private VBox uploadDialog;
    @FXML
    private ImageView imageView;
    private WritableImage image;

    public void openImageAction(ActionEvent actionEvent) throws IOException, ImageProcessingException {
        // Choose image from file
        File file = FileChooserUtil.getFile();
        BufferedImage bufferedImage = ImageIO.read(file);
        image = SwingFXUtils.toFXImage(bufferedImage, null);

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

    // TODO: image format converter & filter

    // TODO: download image
    public void navDownloadAction(ActionEvent actionEvent) {

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