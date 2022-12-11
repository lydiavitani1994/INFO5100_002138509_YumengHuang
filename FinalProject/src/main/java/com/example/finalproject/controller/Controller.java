package com.example.finalproject.controller;

import com.drew.imaging.ImageProcessingException;
import com.example.finalproject.component.Image;
import com.example.finalproject.component.ImageList;
import com.example.finalproject.utility.FileChooserUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private final List<String> supportedFormats = Arrays.asList("JPG", "JPEG", "PNG", "SVG", "BMP", "TIF", "TIFF");
    private final List<String> outputSizes = Arrays.asList("Original Size", "100px wide * 100px high", "360px wide * 360px high", "820px wide * 312px high");
    @FXML
    public VBox imageProperties;
    public VBox imageViewContainer;
    public ScrollPane imageViewScrollPane;
    ImageList imageList = new ImageList();
    @FXML
    private ToggleGroup format;
    @FXML
    private ToggleGroup size;
    @FXML
    private VBox uploadDialog;
    @FXML
    private int inputImageWidth;
    private int inputImageHeight;
    private int outputImageWidth;
    private int outputImageHeight;
    private String inputFormatName;
    private String outputFormatName;
    private List<File> files;
    private BufferedImage outputBufferedImage;
    private BufferedImage inputBufferedImage;

    public void addImageAction() throws IOException, ImageProcessingException {
        // Add image from file and add into imageList
        files = FileChooserUtil.getFiles();
        imageList.addMultipleImages(files);

        // Create UI components for image files
        if (imageList.getNumberOfImages() > 0) {
            // Visibility setting of relative components
            imageViewScrollPane.setVisible(true);
            uploadDialog.setVisible(false);

            for (Image image : imageList.getImageList()) {
                // Create panel for each image file
                HBox hbox = new HBox();
                hbox.setPrefSize(800, 150);
                hbox.setPadding(new Insets(10, 10, 10, 10));
                hbox.setSpacing(10);

                // Set the fx:id of panel same as the image id it contains
                hbox.setId(String.valueOf(image.getId()));

                // Add thumbnail component to the panel
                ImageView thumbNail = new ImageView();
                thumbNail.setFitHeight(100);
                thumbNail.setFitWidth(100);
                thumbNail.setImage(image.getFXImage());

                // Add image information and display in panel
                VBox imageProperties = new VBox();
                imageProperties.setPrefSize(350, 150);
                imageProperties.setSpacing(5);
                imageProperties.getChildren().add(new Label("Name" + ": " + image.getFileName()));
                imageProperties.getChildren().add(new Label("Type" + ": " + image.getFileType()));
                imageProperties.getChildren().add(new Label("Size" + ": " + image.getFileSize()));
                imageProperties.getChildren().add(new Label("Height" + ": " + image.getFileHeight()));
                imageProperties.getChildren().add(new Label("Width" + ": " + image.getFileWidth()));

                // Add choice box for formats
                ChoiceBox<String> formats = new ChoiceBox<>();
                formats.setPrefWidth(100);
                for (String format : supportedFormats) {
                    formats.getItems().add(format);
                }
                formats.setValue(supportedFormats.get(0));
                // TODO: format configuration

                // Add choice box for output sizes
                ChoiceBox<String> sizes = new ChoiceBox<>();
                sizes.setPrefWidth(150);
                for (String size : outputSizes) {
                    sizes.getItems().add(size);
                }
                sizes.setValue(outputSizes.get(0));
                // TODO: size configuration

                // Add download and delete button for each image in panel
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

                });

                // Add download button for each image in panel
                Button downloadBtn = new Button("Download");
                downloadBtn.setPrefWidth(90);
                // TODO: setOnAction to download a specific image

                // Append all child nodes to parent node
                hbox.getChildren().add(thumbNail);
                hbox.getChildren().add(imageProperties);
                hbox.getChildren().add(formats);
                hbox.getChildren().add(sizes);
                hbox.getChildren().add(deleteBtn);
                hbox.getChildren().add(downloadBtn);
                imageViewContainer.getChildren().add(hbox);
            }

        }

        // TODO: 根据你的需要看看需要调整哪些
//        inputBufferedImage = ImageIO.read(file);
//        inputImageWidth = inputBufferedImage.getWidth();
//        inputImageHeight = inputBufferedImage.getHeight();
//        long imageSize = file.length();
//        ImageInputStream iis = ImageIO.createImageInputStream(file);
//        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
//        if (!iter.hasNext()) {
//            throw new RuntimeException("No readers found!");
//        }
//        ImageReader reader = iter.next();
//        inputFormatName = reader.getFormatName();
//
//        System.out.println("image_width" + inputImageWidth);
//        System.out.println("image_height" + inputImageHeight);
//        System.out.println("image_size" + imageSize);
//        System.out.println("image_format" + inputFormatName);
    }

    // image format & size converter
    public void downloadAllAction(ActionEvent actionEvent) throws IOException {
        // format
        RadioButton imageFormatButton = (RadioButton) format.getSelectedToggle();
        if (imageFormatButton == null) {
            outputFormatName = inputFormatName;
        } else {
            outputFormatName = imageFormatButton.getText();
            if (outputFormatName.equals("original format")) {
                outputFormatName = inputFormatName;
            }
        }
        // size
        RadioButton imageSizeButton = (RadioButton) size.getSelectedToggle();
        String sizeValue = "original size";
        if (imageSizeButton != null) {
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

    public void deleteAllImagesAction() {
        // Delete items in imageList
        imageList.deleteAllImages();

        // Delete panels in imageViewContainer
        imageViewContainer.getChildren().clear();
    }
}