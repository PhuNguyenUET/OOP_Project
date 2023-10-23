package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FolderController {
    @FXML
    private Button backBtn;

    public void initialize()
    {
        backBtn.setOnAction(e->{
            ScreenManager.getInstance().switchToLearner();
        });
    }
}



