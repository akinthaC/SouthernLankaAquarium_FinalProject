package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.Db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.repository.EmployeeRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportFormController {

    @FXML
    private JFXButton btn7Profit;

    @FXML
    private JFXButton btnAstimate;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnOredrs;

    @FXML
    private JFXButton btnYearProfit;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;
    public void initialize() throws IOException {
        setDate();
        setTime();

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


    @FXML
    void btn7ProfitONAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/7dayprofit.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("date",lblDate.getText());
        data.put("time",lblTime.getText());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

    @FXML
    void btnAstimateONAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/AstimateProfit&days.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("date",lblDate.getText());
        data.put("time",lblTime.getText());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {

    }

    @FXML
    void btnOredrsOnAction(ActionEvent event) {

    }

    @FXML
    void btnYearProfitONAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/lastYearProfit.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("data",lblDate.getText());
        data.put("time",lblTime.getText());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }
    @FXML
    void btnTodayProfitONAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/oneDayProfit.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("data",lblDate.getText());
        data.put("time",lblTime.getText());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);


    }

}
