package com.example.demo.frontend.ProfileFrontEnd;

import com.example.demo.ScreenManager;
import com.example.demo.backend.LearnerBackend.UserBackend;
import com.example.demo.backend.ProfileBackend.ProfileConection;
import com.example.demo.backend.ProfileBackend.ProfileRepo;
import com.example.demo.backend.ProfileBackend.TimeUsage;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProfileController {
    @FXML
    private Rectangle rec1;

    @FXML
    private Rectangle rec2;
    @FXML
    private Rectangle rec3;
    @FXML
    private Rectangle rec4;

    @FXML
    private Label text1;

    @FXML
    private Label text2;

    @FXML
    private Label text3;

    @FXML
    private Label text4;

    @FXML
    private GridPane dayRender;

    @FXML
    private Label userName;

    @FXML
    private Label currentYear;

    @FXML
    private Label currentMonth;

    @FXML
    private Label currentMonthAndYear;

    @FXML
    private Label streak;
    private UserBackend userBackend = new UserBackend();

    private ProfileRepo profileRepo = new ProfileRepo();

    int curDay = java.time.LocalDate.now().getDayOfMonth();

    List<TimeUsage> list = new ArrayList<>();

    double rec1Height = 0;

    double rec2Height = 0;

    double rec3Height = 0;

    double rec4Height = 0;

    public void initialize() {
//        System.out.println("Hình 1 có chiều cao là: " + rec1.getHeight());
//        System.out.println("Hình 2 có chiều cao là: " + rec2.getHeight());
//        System.out.println("Hình 3 có chiều cao là: " + rec3.getHeight());
//        System.out.println("Hình 4 có chiều cao là: " + rec4.getHeight());
        list = profileRepo.getRecentTimeUsage(ScreenManager.getInstance().getUserId());

        LocalDate currentDate = LocalDate.now();

        Month curMonth = currentDate.getMonth();

        String formattedMonth = curMonth.name().substring(0, 1) + curMonth.name().substring(1).toLowerCase();

        userName.setText(userBackend.getNameById(ScreenManager.getInstance().getUserId()));

        currentMonth.setText(formattedMonth);

        currentYear.setText(String.valueOf(currentDate.getYear()));

        currentMonthAndYear.setText(formattedMonth + " " + currentDate.getYear());

        streak.setText(String.valueOf(userBackend.getStreak(ProfileConection.getInstance().connect(), ScreenManager.getInstance().getUserId())));

        handleChart(list);

        animationForBarChart(rec1, rec2, rec3, rec4, rec1Height, rec2Height, rec3Height, rec4Height);

        StringBuilder firstDateOfMonth = new StringBuilder();
        firstDateOfMonth.append(currentDate.getYear()).append("-").append(currentDate.getMonthValue()).append("-1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate startDateOfMonth = LocalDate.parse(firstDateOfMonth.toString(), formatter);
        String startDayOfMonth = getDayOfWeek(startDateOfMonth);
        int startColIndex = getStartColIndex(startDayOfMonth);
        calendarRender(startColIndex, 0, 30);
    }

    private void handleChart(List<TimeUsage> timeUsages) {
        if (timeUsages.size() >= 1) {
            text1.setText(timeUsages.get(0).getDate());
            rec1Height = timeUsages.get(0).getTime();
        }
        if (timeUsages.size() >= 2)
        {
            text2.setText(timeUsages.get(1).getDate());
            rec2Height = timeUsages.get(1).getTime();
        }
        if (timeUsages.size() >= 3)
        {
            text3.setText(timeUsages.get(2).getDate());
            rec3Height = timeUsages.get(1).getTime();
        }
        if (timeUsages.size() >= 4)
        {
            text4.setText(timeUsages.get(3).getDate());
            rec4Height = timeUsages.get(1).getTime();
        }
    }

    private void animationForBarChart(Rectangle rect1, Rectangle rect2, Rectangle rect3, Rectangle rect4, double rec1Height, double rec2Height, double rec3Height, double rec4Height) {
        double durationTime = 3;
        ParallelTransition parallelTransition = new ParallelTransition();
        KeyValue keyValue1 = new KeyValue(rect1.heightProperty(), rec1Height);
        parallelTransition.getChildren().add(new Timeline(new KeyFrame(Duration.seconds(durationTime), keyValue1)));

        // Tạo animation cho rect2
        KeyValue keyValue2 = new KeyValue(rect2.heightProperty(), rec2Height);
        parallelTransition.getChildren().add(new Timeline(new KeyFrame(Duration.seconds(durationTime), keyValue2)));

        // Tạo animation cho rect3
        KeyValue keyValue3 = new KeyValue(rect3.heightProperty(), rec3Height);
        parallelTransition.getChildren().add(new Timeline(new KeyFrame(Duration.seconds(durationTime), keyValue3)));

        // Tạo animation cho rect4
        KeyValue keyValue4 = new KeyValue(rect4.heightProperty(), rec4Height);
        parallelTransition.getChildren().add(new Timeline(new KeyFrame(Duration.seconds(durationTime), keyValue4)));
        // Bắt đầu animation
        parallelTransition.play();
    }

    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();

        // Lấy tháng hiện tại
        Month currentMonth = currentDate.getMonth();

        int currentYaer = currentDate.getYear();
        // In ra tên tháng
        System.out.println("Tháng hiện tại là: " + currentMonth);
        System.out.println("Năm hiện tại là: " + currentYaer);
        System.out.println(currentDate.getDayOfMonth());
    }

    private void calendarRender(int startColIndex, int startRowIndex, int numberOfDays) {
        for (int i = 1; i <= numberOfDays; i++) {
            startColIndex = startColIndex % 7;
            VBox dayContainer = new VBox();
            dayContainer.setAlignment(Pos.CENTER);
            dayContainer.getStyleClass().add("numberOfDateContainer");
            if (i <= curDay) {
                StringBuilder dateIth = new StringBuilder();
                dateIth.append(java.time.LocalDate.now().getYear()).append("-").append(java.time.LocalDate.now().getMonthValue()).append("-").append(i);
                DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-M-d");
                LocalDate dateI = LocalDate.parse(dateIth.toString(), f);
                if (profileRepo.checkDateIsExist(dateI, ScreenManager.getInstance().getUserId())) {
                    if (!dayContainer.getStyleClass().contains("active")) {
                        dayContainer.getStyleClass().add("active");
                    }
                } else if (i != curDay) {
                    if (!dayContainer.getStyleClass().contains("negative")) {
                        dayContainer.getStyleClass().add("negative");
                    }
                } else if (i == curDay) {
                    if (!dayContainer.getStyleClass().contains("active")) {
                        dayContainer.getStyleClass().add("active");
                    }
                }
            }
            Label day = new Label(String.valueOf(i));
            day.getStyleClass().add("numberOfText");
            dayContainer.getChildren().add(day);
            dayRender.add(dayContainer, startColIndex, startRowIndex);
            startColIndex++;
            if (startColIndex >= 7) {
                startRowIndex++;
            }
        }
    }


    private int getStartColIndex(String day) {
        String formattedDay = day.toLowerCase();
        switch (formattedDay) {
            case "monday":
                return 0;
            case "tuesday":
                return 1;
            case "wednesday":
                return 2;
            case "thursday":
                return 3;
            case "friday":
                return 4;
            case "saturday":
                return 5;
            case "sunday":
                return 6;
            default:
                return -1;
        }
    }


    private String getDayOfWeek(LocalDate date) {
        // Lấy thứ của ngày
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Định dạng thứ thành chuỗi và trả về
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
