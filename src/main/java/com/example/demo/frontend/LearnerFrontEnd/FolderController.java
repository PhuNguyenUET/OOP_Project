package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.LearnerBackend.FolderReposity;
import com.example.demo.backend.LearnerBackend.ListReposity;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;

public class FolderController {
    @FXML
    private Button backBtn;

    @FXML
    private VBox folder_1;

    @FXML
    private FlowPane folderContainer;

    @FXML
    private TranslateTransition toastMesTransition;

    @FXML

    private Button editBtn;

    @FXML
    private HBox toastMes;

    @FXML
    public ImageView toastIcon;

    @FXML

    private Image toastImg;

    @FXML
    private Label textMessage;

    @FXML
    private Label textMessageDes;

    @FXML

    private HBox addBtn;

    @FXML

    private VBox addFolder;

    @FXML

    private Button addFolderName;

    @FXML

    private TextField inputForm;

    private VBox visibleChangeContainer = null;

    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));

    private LearnerScreenChanger leanerScreenChanger = new LearnerScreenChanger();

    public void initialize() {
        toastMesTransition = new TranslateTransition(Duration.seconds(0.75), toastMes);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));


        pauseTransition.setOnFinished(e -> {
            toastMesTransition.setToX(360);
            toastMesTransition.play();
        });

        backBtn.setOnAction(e -> {
            ScreenManager.getInstance().switchToLearner();
        });

        addBtn.setOnMouseClicked(e -> {
            e.consume();
            addFolder.setVisible(!addFolder.isVisible());
            inputForm.requestFocus();
        });

        addFolderName.setOnAction(e -> {
            if (FolderReposity.getInstance().canToAddFolder() && !inputForm.getText().equals("")) {
                FolderReposity.getInstance().addNewFolder(inputForm.getText().trim());
                folderContainerRender();
            } else if (!inputForm.getText().equals("")) {
                URL imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                Image image = new Image(imageUrl.toString());
                textMessage.setText("Limit Folder");
                textMessageDes.setText("You have reached the limit for creating new folders.");
                toastIcon.setImage(image);
                toastMesTransition.setToX(-28);
                toastMesTransition.play();
                pauseTransition.play();
            } else {
                URL imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                Image image = new Image(imageUrl.toString());
                textMessage.setText("Invalid Folder Name");
                textMessageDes.setText("Please try again!");
                toastIcon.setImage(image);
                toastMesTransition.setToX(-28);
                toastMesTransition.play();
                pauseTransition.play();
            }
            addFolder.setVisible(false);
        });

        ScreenManager.getInstance().getScene().setOnMouseClicked(e -> {
            addFolder.setVisible(false);
            if (visibleChangeContainer != null) {
                visibleChangeContainer.setVisible(false);
                visibleChangeContainer = null;
            }
        });

        addFolder.setOnMouseClicked(e -> {
            e.consume();
            inputForm.requestFocus();
        });


        folderContainerRender();
    }

    public void folderContainerRender() {
        List<Folder> folderList = FolderManager.getIntance().updateAndGetListFolder();
        folderContainer.getChildren().clear();
        for (Folder item : folderList) {
            StackPane containerStack = new StackPane();
            VBox containerVBox = new VBox();
            containerVBox.setPrefHeight(240);
            containerVBox.setPrefWidth(240);
            containerVBox.setAlignment(Pos.CENTER);
            containerVBox.setSpacing(70);
            containerVBox.setId(String.valueOf(item.getId()));
            containerVBox.getStyleClass().add("folderContainer");
            HBox containerEdit = new HBox();
            containerEdit.setAlignment(Pos.TOP_RIGHT);
            String imagePath = "/com/example/demo/assets/edit.png";
            URL imageUrl = getClass().getResource(imagePath);
            ImageView editImg = new ImageView(imageUrl.toString());
            editImg.setTranslateX(-8);
            editImg.getStyleClass().add("edit");
            containerEdit.getChildren().add(editImg);
            HBox containerHBox = new HBox();
            containerHBox.setAlignment(Pos.CENTER);
            Label label = new Label(item.getName());
            label.getStyleClass().add("folderName");
            containerHBox.getChildren().add(label);
            HBox containerDelete = new HBox();
            containerDelete.setAlignment(Pos.TOP_RIGHT);
            String imageDeletePath = "/com/example/demo/assets/delete.png";
            URL imageDeleteUrl = getClass().getResource(imageDeletePath);
            ImageView deleteImg = new ImageView(imageDeleteUrl.toString());
            deleteImg.setTranslateX(-8);
            deleteImg.getStyleClass().add("edit");
            containerDelete.getChildren().add(deleteImg);
            containerVBox.getChildren().addAll(containerEdit, containerHBox, containerDelete);
            VBox changeContainer = new VBox();
            changeContainer.setVisible(false);
            changeContainer.setAlignment(Pos.CENTER);
            changeContainer.setSpacing(15);
            changeContainer.getStyleClass().add("addFolder");
            StackPane.setAlignment(changeContainer, Pos.TOP_RIGHT);
            changeContainer.setTranslateX(-18);
            changeContainer.setTranslateY(0);
            changeContainer.setMaxHeight(90);
            changeContainer.setMaxWidth(180);
            TextField changeTextField = new TextField();
            changeTextField.getStyleClass().add("inputForm");
            changeTextField.setPromptText("Folder Name");
            changeTextField.setText(item.getName());
            Button changeBtn = new Button();
            changeBtn.getStyleClass().add("addFolderName");
            changeBtn.setText("Change");
            changeContainer.getChildren().addAll(changeTextField, changeBtn);
            containerStack.getChildren().addAll(containerVBox, changeContainer);
            folderContainer.getChildren().add(containerStack);
            containerVBox.setOnMouseClicked(e -> {
                FolderReposity.getInstance().addRecentFolder(item.getId());
//                ScreenManager.getInstance().switchToList(item.getId());
                leanerScreenChanger.switchToList(item.getId());
            });

            deleteImg.setOnMouseClicked(e -> {
                e.consume();
                FolderReposity.getInstance().removeFolder(item.getId());
                ListReposity.getInstance().removeAllListInFolder(item.getId());
                folderContainerRender();
            });

            editImg.setOnMouseClicked(e -> {
                e.consume();
                if (visibleChangeContainer != null) {
                    visibleChangeContainer.setVisible(false);
                }
                changeContainer.setVisible(true);
                visibleChangeContainer = changeContainer;
            });

            changeBtn.setOnAction(e -> {
                if (!FolderReposity.getInstance().changeFolderName(item.getId(), changeTextField.getText().trim())) {
                    toastMesTransition = new TranslateTransition(Duration.seconds(0.75), toastMes);
                    URL imgUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                    Image image = new Image(imgUrl.toString());
                    textMessage.setText("Invalid Folder Name");
                    textMessageDes.setText("Please try again!");
                    toastIcon.setImage(image);
                    toastMesTransition.setToX(-28);
                    toastMesTransition.play();
                    pauseTransition.play();
                } else {
                    folderContainerRender();
                }
            });

            pauseTransition.setOnFinished(e -> {
                toastMesTransition.setToX(360);
                toastMesTransition.play();
            });

        }
    }
}



