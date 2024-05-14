package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Db.DbConnection;
import lk.ijse.repository.DashBoardRepo;
import javafx.scene.chart.LineChart;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardFormController {

    @FXML
    private JFXButton btnChangeEmail;

    @FXML
    private JFXButton btnChangeTheme;

    @FXML
    private JFXButton btnPassword;

    @FXML
    private JFXButton btnReports;

    public ImageView settingIcone;
    public AnchorPane MainPain;
    public AnchorPane mainPain;
    public AnchorPane mainPain1;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblECount;

    @FXML
    private Label lblPFish;

    @FXML
    private Label lblTSale;

    @FXML
    private Label lblTime;
    @FXML
    private LineChart<?, ?> LineChart;

    public void initialize() throws IOException, SQLException {
        setDate();
        setTime();
        getTodaySaleCOunt();
        getMostSaleFishWeekly();
        getEmployeeCount();
        LineChar();
        mainPain1.setVisible(false);


    }

    private void getEmployeeCount() throws SQLException {
        String count = DashBoardRepo.getEmployeeCount();
        lblECount.setText(count);
    }

    private void getMostSaleFishWeekly() throws SQLException {
        String description = DashBoardRepo.getMostSaleFishWeekly();
        lblPFish.setText(description);

    }

    private void getTodaySaleCOunt() throws SQLException {
        Date date = Date.valueOf(LocalDate.now());
        String count = DashBoardRepo.getSaleCount(date);
        lblTSale.setText(count);


    }


    private void setTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            LocalTime currentTime = LocalTime.now();

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = currentTime.format(timeFormatter);

            lblTime.setText(formattedTime);
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Animation.INDEFINITE);

        clock.play();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));

    }

    private void LineChar() {
        XYChart.Series series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data("Monday", 8));
        series.getData().add(new XYChart.Data("TuesDay", 10));
        series.getData().add(new XYChart.Data("WendsDay", 20));
        series.getData().add(new XYChart.Data("ThursDay", 5));
        series.getData().add(new XYChart.Data("Friday", 5));
        series.getData().add(new XYChart.Data("Saturday", 9));
        series.getData().add(new XYChart.Data("Sunday", 8));
        LineChart.getData().addAll(series);


    }

    public void SettingOnMousePressed(MouseEvent mouseEvent) throws IOException {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), mainPain1);
        transition.setFromX(mainPain1.getWidth());
        transition.setToX(0);
        transition.play();

        mainPain1.setVisible(true);


    }

    public void SettingOnMousePressed1(MouseEvent mouseEvent) {

        mainPain1.setVisible(false);



    }

    public void SettingOnMouseRelesed(MouseEvent mouseEvent) {
    }

    public void btnPasswordOnAction(ActionEvent actionEvent) throws IOException {
        loadFormWithAtractiveAnimation("/view/changePassword.fxml");

    }

    public void btnChangeEmailOnAction(ActionEvent actionEvent) throws IOException {
        loadFormWithAtractiveAnimation("/view/changeEmail.fxml");
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
    }

    @FXML
    void btnChangeThemeOnAction(ActionEvent event) throws IOException {
    }
    private void loadFormWithAtractiveAnimation(String formPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
        AnchorPane newPane = loader.load();

        newPane.setOpacity(0);
        MainPain.getChildren().add(newPane);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), newPane);
        translateTransition.setFromX(newPane.getWidth());
        translateTransition.setToX(0);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), newPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);


        ScaleTransition zoomIn = new ScaleTransition(Duration.seconds(0.5), newPane);
        zoomIn.setFromX(0.5);
        zoomIn.setFromY(0.5);
        zoomIn.setToX(1.0);
        zoomIn.setToY(1.0);

        // Combine all transitions
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(translateTransition,fadeTransition,zoomIn);
        parallelTransition.setOnFinished(event -> {
            MainPain.getChildren().clear();
            MainPain.getChildren().add(newPane);
        });
        parallelTransition.play();
    }
}