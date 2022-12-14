package com.example.finalproject.component;

import com.drew.imaging.ImageProcessingException;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImagePanel {
    private final ImageView thumbNail;
    private final java.util.List<String> formatList = Arrays.asList("JPG", "JPEG", "PNG", "BMP", "TIF", "TIFF");
    private final List<String> sizeList = Arrays.asList("Original Size", "100px wide * 100px high", "360px wide * 360px high", "820px wide * 312px high");
    private final HBox panel;
    private final VBox imageProperties;
    private final javafx.scene.control.Button deleteBtn;
    private final javafx.scene.control.Button downloadBtn;

    public ImagePanel(Image image) throws ImageProcessingException, IOException {
        // Create panel for each image file
        this.panel = new HBox();
        this.panel.setPrefSize(800, 150);
        this.panel.setPadding(new Insets(10, 10, 10, 10));
        this.panel.setSpacing(10);
        this.panel.setId(String.valueOf(image.getId()));

        // Create thumbnail
        this.thumbNail = new ImageView();
        this.thumbNail.setFitHeight(100);
        this.thumbNail.setFitWidth(100);
        this.thumbNail.setImage(image.getFXImage());

        // Create Image Properties
        this.imageProperties = new VBox();
        this.imageProperties.setPrefSize(350, 150);
        this.imageProperties.setSpacing(5);
        this.imageProperties.getChildren().add(new Label("Name: " + image.getName()));
        this.imageProperties.getChildren().add(new Label("Type: " + image.getInputFormat()));
        this.imageProperties.getChildren().add(new Label("Size: " + image.getImageSize()));
        this.imageProperties.getChildren().add(new Label("Height: " + image.getInputHeightStr()));
        this.imageProperties.getChildren().add(new Label("Width: " + image.getInputWidthStr()));

        // Choice box for formats
        ChoiceBox<String> formatChoiceBox = new ChoiceBox<>();
        formatChoiceBox.setPrefWidth(100);
        for (String format : formatList) {
            formatChoiceBox.getItems().add(format);
        }
        formatChoiceBox.setValue(formatList.get(0));

        // Set output format
        image.setOutputFormat(formatList.get(0));
        formatChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> image.setOutputFormat(formatList.get(new_val.intValue())));

        // Choice box for output sizes
        ChoiceBox<String> sizeChoiceBox = new ChoiceBox<>();
        sizeChoiceBox.setPrefWidth(150);
        for (String size : sizeList) {
            sizeChoiceBox.getItems().add(size);
        }
        sizeChoiceBox.setValue(sizeList.get(0));

        // Set output size
        image.setOutputSizeOriginal();
        sizeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    switch (sizeList.get(new_val.intValue())) {
                        case ("100px wide * 100px high") -> image.setOutputSize(100, 100);
                        case ("360px wide * 360px high") -> image.setOutputSize(360, 360);
                        case ("820px wide * 312px high") -> image.setOutputSize(820, 312);
                        default -> image.setOutputSizeOriginal();
                    }
                });

        // delete button for each image
        deleteBtn = new Button("Delete");
        deleteBtn.setPrefWidth(90);

        // download button for each image
        downloadBtn = new Button("Download");
        downloadBtn.setPrefWidth(90);

        // Append all child nodes to parent node
        this.panel.getChildren().add(this.thumbNail);
        this.panel.getChildren().add(this.imageProperties);
        this.panel.getChildren().add(formatChoiceBox);
        this.panel.getChildren().add(sizeChoiceBox);
        this.panel.getChildren().add(deleteBtn);
        this.panel.getChildren().add(downloadBtn);
    }

    public HBox getPanel() {
        return panel;
    }

    public Button getDeleteBtn() {
        return deleteBtn;
    }

    public Button getDownloadBtn() {
        return downloadBtn;
    }


}
