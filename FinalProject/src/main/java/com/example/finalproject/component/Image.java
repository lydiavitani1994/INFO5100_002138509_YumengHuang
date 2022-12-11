package com.example.finalproject.component;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private static int identification = 0;
    private final int id;
    private File file;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String fileHeight;
    private String fileWidth;
    private BufferedImage bufferedImage;
    private javafx.scene.image.Image FXImage;

    public Image(File file) throws ImageProcessingException, IOException {
        identification++;
        id = identification;
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String description = tag.getDescription();
                switch (tagName) {
                    case ("File Name"):
                        this.fileName = description;
                        break;
                    case ("Detected File Type Name"):
                        this.fileType = description;
                        break;
                    case ("File Size"):
                        this.fileSize = description;
                        break;
                    case ("Image Height"):
                        this.fileHeight = description;
                        break;
                    case ("Image Width"):
                        this.fileWidth = description;
                        break;
                    default:
                        continue;
                }
            }
        }
        bufferedImage = ImageIO.read(file);
        FXImage = SwingFXUtils.toFXImage(bufferedImage, null);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public javafx.scene.image.Image getFXImage() {
        return FXImage;
    }

    public File getFile() {
        return file;
    }

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileHeight() {
        return fileHeight;
    }

    public String getFileWidth() {
        return fileWidth;
    }
}
