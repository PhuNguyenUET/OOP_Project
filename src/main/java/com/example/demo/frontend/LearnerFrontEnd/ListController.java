package com.example.demo.frontend.LearnerFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.LearnerBackend.FolderReposity;
import com.example.demo.backend.LearnerBackend.ListReposity;
import com.example.demo.backend.LearnerBackend.WordReposity;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;

public class ListController {
    @FXML
    private Button backBtn;

    @FXML

    private Button folderBtn;

    @FXML
    private VBox ContainerLeft;

    @FXML
    private VBox ContainerRight;

    @FXML

    private HBox addBtn;

    @FXML
    private TranslateTransition toastMesTransition;

    @FXML

    private VBox addFolder;

    @FXML

    private Button addFolderName;

    @FXML

    private TextField inputForm;

    private HBox visibleChangeContainer = null;

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

    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));

    public void initialize() {
        toastMesTransition = new TranslateTransition(Duration.seconds(0.75), toastMes);

        backBtn.setOnAction(e -> {
            ScreenManager.getInstance().switchToFolder();
        });

        folderBtn.setText(FolderReposity.getInstance().getFolderName(ScreenManager.getInstance().getFolderId()));

        listContainerRender();

        addBtn.setOnMouseClicked(e -> {
            e.consume();
            addFolder.setVisible(!addFolder.isVisible());
            inputForm.requestFocus();
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

        addFolderName.setOnAction(e -> {
            if (ListReposity.getInstance().canAddNewListIntoFolder(ScreenManager.getInstance().getFolderId()) && !inputForm.getText().equals("")) {
                ListReposity.getInstance().addNewList(inputForm.getText().trim(), ScreenManager.getInstance().getFolderId());
                listContainerRender();
            } else if (!inputForm.getText().equals("")) {
                URL imageUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                Image image = new Image(imageUrl.toString());
                textMessage.setText("Limit List");
                textMessageDes.setText("You have reached the limit for creating new list.");
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
    }

    public void listContainerRender() {
        List<ListUser> listNameInFolder = ListManager.getInstance().updateAndGetAllListOfFolder(ScreenManager.getInstance().getFolderId());
        int n = listNameInFolder.size();
        ContainerLeft.getChildren().clear();
        ContainerRight.getChildren().clear();
        for (int i = 0; i < n; i++) {
            String name = listNameInFolder.get(i).getName();
            int id = listNameInFolder.get(i).getId();
            System.out.println("ListId: "  + id + " Name: " + name);
            StackPane containerStack = new StackPane();
            HBox containerBtn = new HBox();
            Button listBtn = new Button();
            listBtn.setText(name);
            listBtn.getStyleClass().add("listBtn");
            containerBtn.setAlignment(Pos.CENTER);
            containerBtn.getChildren().add(listBtn);
            String editPath = "/com/example/demo/assets/edit.png";
            URL editUrl = getClass().getResource(editPath);
            ImageView editImg = new ImageView(editUrl.toString());
            String deletePath = "/com/example/demo/assets/delete.png";
            URL deleteUrl = getClass().getResource(deletePath);
            ImageView deleteImg = new ImageView(deleteUrl.toString());
            StackPane.setAlignment(editImg, Pos.CENTER_LEFT);
            StackPane.setAlignment(deleteImg, Pos.CENTER_RIGHT);
            editImg.setTranslateX(90);
            deleteImg.setTranslateX(-90);
            HBox changeContainer = new HBox();
            changeContainer.setVisible(false);
            changeContainer.setAlignment(Pos.CENTER);
            changeContainer.setSpacing(20);
            changeContainer.getStyleClass().add("addFolder");
            StackPane.setAlignment(changeContainer, Pos.CENTER_LEFT);
            changeContainer.setTranslateX(-120);
            changeContainer.setTranslateY(0);
            changeContainer.setMaxHeight(90);
            changeContainer.setMaxWidth(300);
            TextField changeTextField = new TextField();
            changeTextField.getStyleClass().add("inputForm");
            changeTextField.setPromptText("Folder Name");
            changeTextField.setText(name);
            Button changeBtn = new Button();
            changeBtn.getStyleClass().add("addFolderName");
            changeBtn.setText("Change");
            changeContainer.getChildren().addAll(changeTextField, changeBtn);
            containerStack.getChildren().addAll(containerBtn, editImg, deleteImg, changeContainer);
            if (i < 5) {
                ContainerLeft.getChildren().add(containerStack);
            } else {
                ContainerRight.getChildren().add(containerStack);
            }
            listBtn.setOnAction(e -> {
//                System.out.println(name);
                ScreenManager.getInstance().switchToWord(id);
            });

            deleteImg.setOnMouseClicked(e->{
                ListReposity.getInstance().removeListWithId(id);
                WordReposity.getInstance().removeAllListInFolder(id);
                listContainerRender();
            });

            editImg.setOnMouseClicked(e -> {
                e.consume();
                if (visibleChangeContainer != null) {
                    visibleChangeContainer.setVisible(false);
                }
                changeContainer.setVisible(true);
                visibleChangeContainer = changeContainer; // Lưu trữ `changeContainer` hiện đang hiển thị
            });

            System.out.println(id);
            changeBtn.setOnAction(e->{
                System.out.println(changeTextField.getText() + " " + id);
                if(!ListReposity.getInstance().changeListName(id, changeTextField.getText().trim())){
                    toastMesTransition = new TranslateTransition(Duration.seconds(0.75), toastMes);
                    URL imgUrl = getClass().getResource("/com/example/demo/assets/cross.png");
                    Image image = new Image(imgUrl.toString());
                    textMessage.setText("Invalid List Name");
                    textMessageDes.setText("Please try again!");
                    toastIcon.setImage(image);
                    toastMesTransition.setToX(-28);
                    toastMesTransition.play();
                    pauseTransition.play();
                }
                else
                {
                    listContainerRender();
                }
            });

            pauseTransition.setOnFinished(e -> {
                toastMesTransition.setToX(360);
                toastMesTransition.play();
            });
        }
    }
}
