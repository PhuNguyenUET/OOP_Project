package com.example.demo.frontend.FlipGameFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.FlipGameBackend.FlipGameService;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.*;

public class FlipGameController {

    @FXML
    private VBox GameContainer;

    Game game = new Game(GameScreenChanger.getIntance().getTopicName(), GameScreenChanger.getIntance().getPlayer1Name(), GameScreenChanger.getIntance().getPlayer2Name());

    List<ImgOfGame> list = game.getList();

    boolean[] isFrontShowing = new boolean[40];

    boolean[] isAnimationRunning = new boolean[40];


    List<StackPane> allStackPanes = new ArrayList<>();

    Queue<StackPane> q = new LinkedList<>();
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    boolean player1GetScore = true;

    Player player1 = game.getPlayer1();
    Player player2 = game.getPlayer2();

    @FXML
    private VBox score1;

    @FXML
    private VBox score2;

    @FXML
    private VBox resultContainer;

    @FXML
    private Label textResult;

    @FXML
    private Button playAgainBtn;

    @FXML
    private Button gameHomeBtn;

    private TranslateTransition transition;


    public void initialize() {
        render();
        renderScore1(player1);
        renderScore2(player2);
        for (int i = 0; i < allStackPanes.size(); i++) {
            StackPane st = allStackPanes.get(i);
            int index = (int) st.getProperties().get("index");
            int runValue = i;
            if (index < list.size()) {
                st.setOnMouseClicked(event -> {
                    handleFlip(st, index, 0, runValue);
                });
            } else {
                st.setOnMouseClicked(event -> {
                    handleFlip(st, index, 1, runValue);
                });
            }
        }

        playAgainBtn.setOnAction(e->{
            GameScreenChanger.getIntance().switchToSelectTopic();
        });

        gameHomeBtn.setOnAction(e->{
            ScreenManager.getInstance().switchToGame();
        });
    }

    public void itemRender(int index) {
        StackPane containerStackPane = new StackPane();
        VBox containerVbox = new VBox();
        ImageView imageView = new ImageView();
        if (index < list.size()) {
            imageView.setImage(list.get(index).getImage());
        } else {
            imageView.setImage(new Image(getClass().getResource("/com/example/demo/assets/space.png").toString()));
        }
        containerVbox.getChildren().add(imageView);
        containerStackPane.getChildren().add(containerVbox);
    }

    public void renderRow(int indexRow) {
        HBox rowContainer = new HBox();
        for (int i = 0; i < 8; i++) {
            int index = i + indexRow * 8;
            StackPane containerStackPane = new StackPane();
            VBox containerVbox = new VBox();
            ImageView imageView = new ImageView();
            if (index < list.size()) {
                imageView.setImage(list.get(index).getImage());
            } else {
                imageView.setImage(new Image(getClass().getResource("/com/example/demo/assets/space.png").toString()));
            }
            containerVbox.getChildren().add(imageView);
            containerStackPane.getChildren().add(containerVbox);
            rowContainer.getChildren().add(containerStackPane);
        }

    }

    public void render() {

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 8; i++) {
                int index = i + j * 8;
                StackPane containerStackPane = new StackPane();
                containerStackPane.getStyleClass().add("container");
                VBox containerVbox = new VBox();
                containerVbox.getStyleClass().add("container");
                ImageView imageView = new ImageView();
                Rectangle clip = new Rectangle(120, 120);
                clip.setArcWidth(20); // Đặt chiều rộng của cạnh bo tròn
                clip.setArcHeight(20); // Đặt chiều cao của cạnh bo tròn
                imageView.setClip(clip);
                imageView.getStyleClass().add("imageCss");
                String name = "";
                if (index < list.size()) {
                    imageView.setImage(list.get(index).getImage());
                    name = list.get(index).getName();
                    containerVbox.getChildren().add(imageView);
                    containerStackPane.getChildren().add(containerVbox);
                } else {
                    int val = index - list.size();
                    Label label = new Label(list.get(val).getName());
                    name = list.get(val).getName();
                    label.getStyleClass().add("name");
                    imageView.setImage(new Image(getClass().getResource("/com/example/demo/assets/bg.png").toString()));
                    containerVbox.getChildren().add(imageView);
                    StackPane.setAlignment(label, Pos.CENTER);
                    containerStackPane.getChildren().addAll(containerVbox, label);
                }
                containerStackPane.getProperties().put("index", index);
                containerStackPane.getProperties().put("text", name);
                allStackPanes.add(containerStackPane);

            }
        }

        for (StackPane stackPane : allStackPanes) {
            VBox imageViewConatainer = (VBox) stackPane.getChildren().get(0);
            ImageView imageView = (ImageView) imageViewConatainer.getChildren().get(0);
            imageView.setImage(new Image(getClass().getResource("/com/example/demo/assets/space.png").toString()));
            if (stackPane.getChildren().size() >= 2) {
                Label label = (Label) stackPane.getChildren().get(1);
                label.setVisible(false);
            }
        }
        // Shuffle the list of StackPane elements
        Collections.shuffle(allStackPanes);

        int index = 0;
        for (int j = 0; j < 5; j++) {
            HBox rowContainer = new HBox();
            rowContainer.setAlignment(Pos.CENTER);
            rowContainer.setSpacing(15);

            for (int i = 0; i < 8; i++) {
                rowContainer.getChildren().add(allStackPanes.get(index));
                index++;
            }

            GameContainer.getChildren().add(rowContainer);
        }
    }

    private void handleFlip(StackPane stackPane, int index, int check, int runningValue) {
        if (isAnimationRunning[runningValue]) {
            // Nếu animation đang chạy, không thực hiện gì cả
            return;
        }

        isAnimationRunning[runningValue] = true;

        RotateTransition rotateFrontTransition = new RotateTransition(Duration.seconds(0.75), stackPane);
        RotateTransition rotateBackTransition = new RotateTransition(Duration.seconds(0.75), stackPane);
        rotateFrontTransition.setAxis(Rotate.Y_AXIS);

        rotateFrontTransition.setFromAngle(0);
        rotateFrontTransition.setToAngle(90);

        rotateFrontTransition.setOnFinished(e -> {
            isFrontShowing[index] = !isFrontShowing[index];
            if (!isFrontShowing[index]) {
                // Chuyển về mặt sau
//                ImageView imageView = (ImageView) stackPane.getChildren().get(0);
                VBox imageViewConatainer = (VBox) stackPane.getChildren().get(0);
                ImageView imageView = (ImageView) imageViewConatainer.getChildren().get(0);
                imageView.setImage(new Image(getClass().getResource("/com/example/demo/assets/space.png").toString()));
                if (check == 1) {
                    Label label = (Label) stackPane.getChildren().get(1);
                    label.setVisible(false);
                }
            } else {
                // Chuyển về mặt trước
                VBox imageViewConatainer = (VBox) stackPane.getChildren().get(0);
                ImageView imageView = (ImageView) imageViewConatainer.getChildren().get(0);
                if (check == 0)
                    imageView.setImage(list.get(index).getImage());
                else
                    imageView.setImage(new Image(getClass().getResource("/com/example/demo/assets/bg.png").toString()));
                if (check == 1) {
                    Label label = (Label) stackPane.getChildren().get(1);
                    label.setVisible(true);
                }
            }
            rotateBackTransition.setFromAngle(90);
            rotateBackTransition.setToAngle(0);
            rotateBackTransition.play();

        });

        rotateBackTransition.setOnFinished(e -> {
            isAnimationRunning[runningValue] = false;
            stackPane.setDisable(true);
            if (stackPane.getChildren().size() >= 2) {
                Label label = (Label) stackPane.getChildren().get(1);
                label.setOpacity(1);
            }
            q.add(stackPane);
            q1.add(index);
            q2.add(runningValue);
            while (q.size() >= 2) {
                System.out.println(q.size());
                StackPane st = q.poll();
                int tmpIndex = q1.poll();
                int tmpRunValue = q2.poll();
                StackPane st2 = q.poll();
                int tmpIndex2 = q1.poll();
                int tmpRunValue2 = q2.poll();
                String resul1 = getStringFromStackPane(st);
                String result2 = getStringFromStackPane(st2);
                if (resul1.equals(result2)) {
                    if (player1GetScore) {
                        player1.setScore(player1.getScore() + 10);
                    } else {
                        player2.setScore(player2.getScore() + 10);
                    }
                    applyFadeOutEffect(st);
                    applyFadeOutEffect(st2);
                    renderScore1(player1);
                    renderScore2(player2);
                    game.setSizeOfGame(game.getSizeOfGame() - 2);
                } else {
                    if (tmpIndex < list.size()) {
                        handleFlipBack(st, tmpIndex, 0, tmpRunValue);
                    } else {
                        handleFlipBack(st, tmpIndex, 1, tmpRunValue);
                    }
                    if (tmpIndex2 < list.size()) {
                        handleFlipBack(st2, tmpIndex2, 0, tmpRunValue2);
                    } else {
                        handleFlipBack(st2, tmpIndex2, 1, tmpRunValue2);
                    }
                    player1GetScore = !player1GetScore;
                }
                if (game.isFinished()) {
                    int play1Score = player1.getScore();
                    int play2Score = player2.getScore();
                    transition = new TranslateTransition(Duration.seconds(3), resultContainer);
                    if (play1Score == play2Score) {
                        textResult.setText("Tỉ số hòa nhau");
                    } else {
                        String result = "Chúc mừng " + game.nameOfWinner()+ " là người chiến thắng";
                        textResult.setText(result);
                    }
                    transition.setToY(240);
                    transition.play();
                }
                System.out.println("Điểm của Player1: " + player1.getScore());
                System.out.println("Điểm của Player2: " + player2.getScore());
            }
        });

        rotateFrontTransition.play();
    }

    public String getStringFromStackPane(StackPane stackPane) {
        String res = "";
        if (stackPane.getChildren().size() >= 2) {
            Label label = (Label) stackPane.getChildren().get(1);
            return label.getText();
        }
        return (String) stackPane.getProperties().get("text");
    }

    private void handleFlipBack(StackPane stackPane, int index, int check, int runningValue) {

        RotateTransition rotateFrontTransition = new RotateTransition(Duration.seconds(0.75), stackPane);
        RotateTransition rotateBackTransition = new RotateTransition(Duration.seconds(0.75), stackPane);
        rotateFrontTransition.setAxis(Rotate.Y_AXIS);

        rotateFrontTransition.setFromAngle(0);
        rotateFrontTransition.setToAngle(90);

        rotateFrontTransition.setOnFinished(e -> {
            isFrontShowing[index] = !isFrontShowing[index];
            if (!isFrontShowing[index]) {
                // Chuyển về mặt sau
                VBox imageViewConatainer = (VBox) stackPane.getChildren().get(0);
                ImageView imageView = (ImageView) imageViewConatainer.getChildren().get(0);
                imageView.setImage(new Image(getClass().getResource("/com/example/demo/assets/space.png").toString()));
                if (check == 1) {
                    Label label = (Label) stackPane.getChildren().get(1);
                    label.setVisible(false);
                }
            } else {
                // Chuyển về mặt trước
                VBox imageViewConatainer = (VBox) stackPane.getChildren().get(0);
                ImageView imageView = (ImageView) imageViewConatainer.getChildren().get(0);
                if (check == 0)
                    imageView.setImage(list.get(index).getImage());
                else
                    imageView.setImage(new Image(getClass().getResource("/com/example/demo/assets/bg.png").toString()));
                if (check == 1) {
                    Label label = (Label) stackPane.getChildren().get(1);
                    label.setVisible(true);
                }
            }
            rotateBackTransition.setFromAngle(90);
            rotateBackTransition.setToAngle(0);
            rotateBackTransition.play();

        });

        rotateBackTransition.setOnFinished(e -> {
            stackPane.setDisable(false);
        });

        rotateFrontTransition.play();
    }

    public void applyFadeOutEffect(StackPane stackPane) {
        // Tạo một FadeTransition để thực hiện hiệu ứng mờ dần trong `durationSeconds` giây
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1.5), stackPane);
        fadeOutTransition.setFromValue(1.0); // Độ trong suốt ban đầu
        fadeOutTransition.setToValue(0.0);   // Độ trong suốt cuối cùng

        // Xử lý sự kiện khi kết thúc hiệu ứng
        fadeOutTransition.setOnFinished(event -> {
            stackPane.setVisible(false); // Ẩn StackPane sau khi biến mất
        });

        // Bắt đầu hiệu ứng
        fadeOutTransition.play();
    }

    public void renderScore1(Player player1) {
        score1.getChildren().clear();
        HBox nameContainer = new HBox();
        HBox scoreContainer = new HBox();
        Label nameLabel = new Label(player1.getName());
        Label scoreLabel = new Label(String.valueOf(player1.getScore()));
        nameLabel.getStyleClass().add("playerName");
        scoreLabel.getStyleClass().add("score");
        scoreLabel.setAlignment(Pos.CENTER);
        nameContainer.setAlignment(Pos.CENTER);
        scoreContainer.setAlignment(Pos.CENTER);
        nameContainer.getChildren().add(nameLabel);
        scoreContainer.getChildren().add(scoreLabel);
        score1.getChildren().addAll(nameContainer, scoreContainer);
    }

    public void renderScore2(Player player2) {
        score2.getChildren().clear();
        HBox nameContainer = new HBox();
        HBox scoreContainer = new HBox();
        Label nameLabel = new Label(player2.getName());
        Label scoreLabel = new Label(String.valueOf(player2.getScore()));
        nameLabel.getStyleClass().add("playerName");
        scoreLabel.getStyleClass().add("score");
        scoreLabel.setAlignment(Pos.CENTER);
        nameContainer.setAlignment(Pos.CENTER);
        scoreContainer.setAlignment(Pos.CENTER);
        nameContainer.getChildren().add(nameLabel);
        scoreContainer.getChildren().add(scoreLabel);
        score2.getChildren().addAll(nameContainer, scoreContainer);
    }
}
