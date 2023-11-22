package com.example.demo.frontend.Translator;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslatorController implements Initializable {
    Translator translator = new Translator();
    @FXML
    TextArea sourceField;
    @FXML
    TextArea targetField;
    @FXML
    Label sourceLabel;
    @FXML
    Label targetLabel;
    @FXML
    Button switchButton;

    URL imageUrl = getClass().getResource("/com/example/demo/assets/switch.png");
    Image switchImg = new Image(imageUrl.toString());
    ImageView sw = new ImageView(switchImg);

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
        targetField.setText("Waiting for response...");
        Task<Void> translateThread = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                translator.setSourceText(sourceField.getText());
                translator.translate();
                return null;
            }
        };
        translateThread.setOnSucceeded(event -> targetField.setText(translator.getTargetText()));
        new Thread(translateThread).start();
    }

    @FXML
    public void copy() {
        String myString = translator.getTargetText();
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sw.setFitWidth(50);
        sw.setFitHeight(30);
        switchButton.setGraphic(sw);
    }
}
