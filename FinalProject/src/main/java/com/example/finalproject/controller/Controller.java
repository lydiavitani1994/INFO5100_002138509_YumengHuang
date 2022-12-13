package com.example.finalproject.controller;

import com.drew.imaging.ImageProcessingException;
import com.example.finalproject.component.Image;
import com.example.finalproject.component.ImageList;
import com.example.finalproject.utility.FileChooserUtil;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
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
    ImageList imageList = new ImageList();
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
            HBox hbox = new HBox();
            hbox.setPrefSize(800, 150);
            hbox.setPadding(new Insets(10, 10, 10, 10));
            hbox.setSpacing(10);
            hbox.setId(String.valueOf(image.getId()));

            // choice box for formats
            ChoiceBox<String> formatChoiceBox = new ChoiceBox<>();
            formatChoiceBox.setPrefWidth(100);
            for (String format : formatList) {
                formatChoiceBox.getItems().add(format);
            }
            formatChoiceBox.setValue(formatList.get(0));
            // set output format
            image.setOutputFormat(formatList.get(0));
            formatChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                    (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> image.setOutputFormat(formatList.get(new_val.intValue())));

            // choice box for output sizes
            ChoiceBox<String> sizeChoiceBox = new ChoiceBox<>();
            sizeChoiceBox.setPrefWidth(150);
            for (String size : sizeList) {
                sizeChoiceBox.getItems().add(size);
            }
            sizeChoiceBox.setValue(sizeList.get(0));
            // set output size
            image.setOutputSizeOriginal();
            sizeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                    (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                        switch (sizeList.get(new_val.intValue())) {
                            case ("Original Size") -> image.setOutputSizeOriginal();
                            case ("100px wide * 100px high") -> image.setOutputSize(100, 100);
                            case ("360px wide * 360px high") -> image.setOutputSize(360, 360);
                            case ("820px wide * 312px high") -> image.setOutputSize(820, 312);
                            default -> image.setOutputSizeOriginal();
                        }
                    });

            // delete button for each image
            Button deleteBtn = new Button("Delete");
            deleteBtn.setPrefWidth(90);
            deleteBtn.setOnAction(event -> {
                // Get the parent node and its fx:id
                Node target = (Node) event.getTarget();
                Node parent = target.getParent();
                String IdToDelete = parent.getId();
                // Delete respective image in the imageList with fx:id
                imageList.deleteImage(Integer.parseInt(IdToDelete));
                // Delete respective panel
                imageViewContainer.getChildren().remove(parent);
                if (imageList.getNumberOfImages() == 0) {
                    imageViewScrollPane.setVisible(false);
                    uploadDialog.setVisible(true);
                }
            });

            // download button for each image
            Button downloadBtn = new Button("Download");
            downloadBtn.setPrefWidth(90);
            // setOnAction to download a specific image
            downloadBtn.setOnAction(event -> {
                try {
                    image.downloadSingleImage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            // Append all child nodes to parent node
            hbox.getChildren().add(image.getThumbNail());
            hbox.getChildren().add(image.getImageProperties());
            hbox.getChildren().add(formatChoiceBox);
            hbox.getChildren().add(sizeChoiceBox);
            hbox.getChildren().add(deleteBtn);
            hbox.getChildren().add(downloadBtn);
            imageViewContainer.getChildren().add(hbox);
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