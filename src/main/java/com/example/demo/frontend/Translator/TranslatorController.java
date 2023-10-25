package com.example.demo.frontend.Translator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TranslatorController {
    Translator translator = new Translator();
    @FXML
    TextArea sourceField;
    @FXML
    Label targetField;
    @FXML
    Label sourceLabel;
    @FXML
    Label targetLabel;

    @FXML
    public void changeLanguage() {
        translator.changeLanguage();
        if (translator.isEngToViet()) {
            sourceLabel.setText("English");
            targetLabel.setText("Vietnamese");
        } else {
            sourceLabel.setText("Vietnamese");
            targetLabel.setText("English");
        }
        targetField.setText("");
    }
    @FXML
    public void translate() {
        translator.setSourceText(sourceField.getText());
        translator.translate();
        targetField.setText(translator.getTargetText());
    }
}
