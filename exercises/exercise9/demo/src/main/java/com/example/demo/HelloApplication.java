package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        BorderPane pane = new BorderPane();
//        pane.setPadding(new Insets(200, 300, 200, 300));
//
//        //Creating a GridPane container
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10, 10, 10, 10));
//        grid.setVgap(5);
//        grid.setHgap(5);
//        //Defining the Name text field
//        final TextField num1 = new TextField();
//        num1.setPromptText("Enter your first number");
////        num1.setPrefColumnCount(10);
//        num1.getText();
////        GridPane.setConstraints(num1, 0, 0);
////        grid.getChildren().add(num1);
//
//        grid.add(num1, 0, 0)
//
//        pane.setCenter(grid);
//
//
//        Scene scene = new Scene(pane);
//        stage.setTitle("calculator");
//        stage.setScene(scene);
//        stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}