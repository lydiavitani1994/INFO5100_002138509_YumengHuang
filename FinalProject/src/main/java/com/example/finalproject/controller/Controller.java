package com.example.finalproject.controller;

import com.example.finalproject.utility.FileChooserUtil;
import com.example.finalproject.utility.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;

public class Controller {
    @FXML
    public TextFlow imageProperties;
    @FXML
    public Label alert;
    public HBox imageViewContainer;
    @FXML
    private ToggleGroup format;
    @FXML
    private ToggleGroup filter;
    @FXML
    private VBox uploadDialog;
    @FXML
    private ImageView imageView;
    private WritableImage image;

    public void openImageAction(ActionEvent actionEvent) throws IOException {
        // Choose image from file
        image = FileChooserUtil.getImageFromFile();

        // Resize image to fit window
        ImageUtil.fitImage(image, imageViewContainer, imageView);

        // Display image in window
        imageView.setImage(image);

        // TODO: Display image property in window
        Text property = new Text(imageView.imageProperty().toString());
        imageProperties.getChildren().add(property);

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