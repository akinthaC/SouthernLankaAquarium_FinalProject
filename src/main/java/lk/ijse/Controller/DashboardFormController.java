package lk.ijse.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
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

public class DashboardFormController{

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

    }

    private void getEmployeeCount() throws SQLException {
        String count = DashBoardRepo.getEmployeeCount();
        lblECount.setText(count);
    }

    private void getMostSaleFishWeekly() throws SQLException {
        String description=DashBoardRepo.getMostSaleFishWeekly();
        lblPFish.setText(description);

    }

    private void getTodaySaleCOunt() throws SQLException {
        Date date= Date.valueOf(LocalDate.now());
        String count =DashBoardRepo.getSaleCount(date);
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

    private void LineChar(){
        XYChart.Series series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data("Monday",8)) ;
        series.getData().add(new XYChart.Data("TuesDay",10)) ;
        series.getData().add(new XYChart.Data("WendsDay",20)) ;
        series.getData().add(new XYChart.Data("ThursDay",5)) ;
        series.getData().add(new XYChart.Data("Friday",5)) ;
        series.getData().add(new XYChart.Data("Saturday",9)) ;
        series.getData().add(new XYChart.Data("Sunday",8)) ;
        LineChart.getData().addAll(series);



        }

}
