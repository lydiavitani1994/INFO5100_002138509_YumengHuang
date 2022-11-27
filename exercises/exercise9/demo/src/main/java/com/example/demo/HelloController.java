package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Math.max;

public class HelloController {
    @FXML
    public Label op;
    public TextField result;
    public TextField num1;
    public TextField num2;
    public Label alert;

    @FXML
    public void onPlusClick(ActionEvent event) {
        op.setText("+");
    }

    @FXML
    public void onMinusClick(ActionEvent event) {
        op.setText("-");
    }

    @FXML
    public void onMultiplyClick(ActionEvent event) {
        op.setText("*");
    }

    @FXML
    public void onDivideClick(ActionEvent event) {
        op.setText("/");
    }

    @FXML
    public void onCalculateClick(ActionEvent event) {
        String num1Str = num1.getText();
        String num2Str = num2.getText();
        int num1Type = 1; // int-0  double-1
        int num2Type = 1;
        int resultType = 1;
        if (!num1Str.contains(".")){
            num1Type = 0;
        }
        if (!num2Str.contains(".")){
            num2Type = 0;
        }
        if (num1Type == 0 && num2Type == 0){
            resultType = 0;
        }

        double num1d = 0.0f;
        double num2d = 0.0f;
        boolean num1Correct = true;
        boolean num2Correct = true;

        try{
            num1d = Double.parseDouble(num1Str);
        }catch (NullPointerException e){
            alert.setText("Please input number1!");
            num1Correct = false;
            num1.setStyle("-fx-text-box-border: red ;");
        }catch (NumberFormatException e){
            alert.setText("Please input number1 in vaild format(Integer or Double)!");
            num1.setStyle("-fx-text-box-border: red ;");
            num1Correct = false;
        }

        try{
            num2d = Double.parseDouble(num2Str);
        }catch (NullPointerException e){
            if (num1Correct){
                alert.setText("Please input number2!");
            }else{
                alert.setText(alert.getText() + "\n" + "Please input number2!");
            }
            num2.setStyle("-fx-text-box-border: red ;");
            num2Correct = false;
        }catch (NumberFormatException e){
            if (num1Correct){
                alert.setText("Please input number2 in vaild format(Integer or Double)!");
            }else{
                alert.setText(alert.getText() + "\n" + "Please input number2 in vaild format(Integer or Double)!");
            }
            num2.setStyle("-fx-text-box-border: red ;");
            num2Correct = false;
        }

        if (num1Correct && num2Correct){
            num1.setStyle("-fx-text-box-border: black ;");
            num2.setStyle("-fx-text-box-border: black ;");
            alert.setText("");

            int digit = max(num1Str.length() - (num1Str.indexOf(".") + 1), num2Str.length() - (num2Str.indexOf(".") + 1));
            double resultDouble = 0.0;
            switch (op.getText()){
                case "+":
                    resultDouble = num1d + num2d;
                    break;
                case "-":
                    resultDouble = num1d - num2d;
                    break;
                case "*":
                    resultDouble = num1d * num2d;
                    break;
                case "/":
                    resultDouble = num1d / num2d;
                    break;
            }

            if (resultType == 0 && (!op.getText().equals("/"))){
                result.setText((int)resultDouble + "");
            }else{
                if (op.getText().equals("-")){
                    String resultStr = String.format("%." + digit +"f", resultDouble);
                    result.setText(resultStr);
                }else{
                    result.setText(resultDouble + "");
                }
            }
        }
    }

    @FXML
    public void onClearClick(ActionEvent event) {
        num1.setStyle("-fx-text-box-border: black ;");
        num2.setStyle("-fx-text-box-border: black ;");
        alert.setText("");
        num1.setText("");
        num2.setText("");
        result.setText("");
    }

}