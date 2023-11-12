package com.example.demo.frontend.FlipGameFrontEnd;

import com.example.demo.backend.FlipGameBackend.FlipGameService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class SelectTopic {
    @FXML
    private FlowPane topicContainer;

    @FXML
    private Button backBtn;
    private FlipGameService flipGameService = new FlipGameService();

    public void initialize() {
        render();

        backBtn.setOnAction(e->{
            GameScreenChanger.getIntance().switchToGame2();
        });
    }

    public void render()
    {
        List<String> topics = flipGameService.getAllTopicName();
        for(String topic : topics)
        {
            VBox topicItem = new VBox();
            topicItem.setAlignment(Pos.CENTER);
            topicItem.getStyleClass().add("topicItem");
            Label label = new Label(topic);
            label.getStyleClass().add("topicName");
            topicItem.getChildren().add(label);
            topicContainer.getChildren().add(topicItem);

            topicItem.setOnMouseClicked(e->{
                GameScreenChanger.getIntance().setTopicName(topic);
                GameScreenChanger.getIntance().switchToFlipGame();
            });
        }
    }
}
