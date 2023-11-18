package com.example.demo.frontend.DictionaryFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.LearnerBackend.ConnectComponentLearner;
import com.example.demo.frontend.Common.LearnerFuncToDict;
import com.example.demo.frontend.navBarFrontEnd.NavbarController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DictionaryHomeController implements Initializable {

    private LearnerFuncToDict lf = new ConnectComponentLearner();

    List<Label> folders = new ArrayList<>();

    SearchBarController searchBarController;

    @FXML
    public TextField searchBar;

    @FXML
    public Button xButton;

    @FXML
    public Button searchButton;

    @FXML
    public VBox suggestionBox;

    @FXML
    public ImageView logoImage;

    @FXML
    public VBox listOfLists;

    @FXML
    public Button translateButton;
    @FXML
    public Button settingsButton;

    URL imageUrl = getClass().getResource("/com/example/demo/assets/search.png");
    Image searchImg = new Image(imageUrl.toString());
    ImageView search = new ImageView(searchImg);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search.setFitWidth(35);
        search.setFitHeight(35);
        searchBarController = new SearchBarController();
        suggestionBox.setVisible(false);
        searchButton.setGraphic(search);
        List<String> f = lf.getRecentFolders(ScreenManager.getInstance().getUserId());
        System.out.println(f.size());
        updateRecentFolders(f);

        translateButton.setOnAction(e->{
            ScreenManager.getInstance().switchToTranslate();
            ScreenManager.getInstance().getNavbarController().handleActive(ScreenManager.getInstance().getNavbarController().getTranslate());
        });
    }

    @FXML
    protected void clearSearch(ActionEvent event) {
        searchBarController.clearSearch();
        searchBar.clear();
        suggestionBox.setVisible(false);
    }

    @FXML
    protected void goTo(ActionEvent event) throws IOException {
        searchBarController.searchForWord(event, searchBar.getText());
    }

    @FXML
    protected void showSuggestions() {
        suggestionBox.getChildren().clear();
        searchBarController.setSearchField(searchBar.getText());
        suggestionBox.setVisible(true);
        suggestionBox.getChildren().addAll(searchBarController.getSuggestion());
    }


    @FXML
    protected void handleKey(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            searchBarController.searchForWord(event, searchBarController.getSearchField());
        }
    }

    @FXML
    protected void changeScreenToTranslate(Event event) {
        ScreenManager.getInstance().switchToTranslate();
    }

    @FXML
    protected void changeScreenToSettings(Event event) {
        ScreenManager.getInstance().switchToSettings();
    }

    @FXML
    protected void clearSuggestion(MouseEvent event) {
        suggestionBox.setVisible(false);
    }

    private void updateRecentFolders(List<String> f) {
        folders.clear();
        listOfLists.getChildren().clear();
        System.out.println(f.size());
        for (int i = 0; i < f.size(); i++) {
            String folderName = f.get(i);
            Label temp = new Label(folderName);
            temp.getStyleClass().add("folderLabel");
            folders.add(temp);
            temp.setOnMouseClicked(e -> {
                int folderId = lf.getFolderId(folderName);
                lf.updateRecentFolder(folderName);
                ScreenManager.getInstance().switchToList(folderId);
                ScreenManager.getInstance().getNavbarController().handleActive(ScreenManager.getInstance().getNavbarController().getLearner());
            });
            listOfLists.getChildren().add(temp);
        }
        System.out.println(folders.size());
    }
}
