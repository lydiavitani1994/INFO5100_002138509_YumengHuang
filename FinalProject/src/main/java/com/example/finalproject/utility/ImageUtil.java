package com.example.finalproject.utility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author YWu97
 */
public class ImageUtil {
    public static void fitImage(Image image, HBox imageViewContainer, ImageView imageView) {
        double imgWidth = image.getWidth();
        double imgHeight = image.getHeight();
        double windWidth = imageViewContainer.getWidth();
        double windHeight = imageViewContainer.getHeight();

        double finalHeight, finalWidth;
        finalHeight = Math.min(imgHeight, windHeight);
        finalWidth = Math.min(imgWidth, windWidth);
        if (imgWidth / imgHeight > finalWidth / finalHeight) {
            finalHeight = finalWidth / imgWidth * imgHeight;
        } else {
            finalWidth = finalHeight / imgHeight * imgWidth;
        }

        imageView.setFitHeight(finalHeight);
        imageView.setFitWidth(finalWidth);

        imageViewContainer.setPrefWidth(finalWidth);
        imageViewContainer.setPrefHeight(finalHeight);
    }
}
