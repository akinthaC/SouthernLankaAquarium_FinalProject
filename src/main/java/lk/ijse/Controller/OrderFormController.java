package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.model.*;
import lk.ijse.repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderFormController {

    @FXML
    private JFXButton brnAddTOCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXComboBox<String> cmbAccessorieId;

    @FXML
    private JFXComboBox<String> cmbCusId;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private JFXComboBox<String> cmbFishId;

    @FXML
    private JFXComboBox<?> cmbStatuss;

    @FXML
    private TableColumn<?, ?> colActive;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colHandOverDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmpName;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNormalPrice;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblWholeSalePrice;

    @FXML
    private TableView<?> tblPlaceOrder;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtHandOverDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    public void initialize() {
        setDate();
        setTime();
        getCurrentOrderId();
        getCustomerIds();
        getEmployeeId();
        getFishId();
        getAccessoriesId();
        //setCellValueFactory();
    }

    private void getAccessoriesId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = AccessoriesRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbAccessorieId.setItems(obList);
            cmbFishId.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getFishId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = FishRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbFishId.setItems(obList);
            cmbAccessorieId.getSelectionModel().clearSelection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = EmployeeRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbEmployeeId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void getCurrentOrderId() {
        try {
            String currentId = OrderRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("0");
            int idNum = Integer.parseInt(split[1]);
            return "OR0" + ++idNum;
        }
        return "OR01";
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
    void brnAddTOCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void cmbAccessorieIdOnAction(ActionEvent event) {
        String id = cmbAccessorieId.getValue();
        try {
           Accessories accessories = AccessoriesRepo.searchById(id);

            if (accessories != null) {

                lblName.setText(accessories.getName());
                lblQtyOnHand.setText(accessories.getQty());
                lblNormalPrice.setText(String.valueOf(accessories.getNormalPrice()));
                lblWholeSalePrice.setText(String.valueOf(accessories.getWholesaleprice()));
                cmbFishId.getSelectionModel().clearSelection();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbCusIdOnAction(ActionEvent event) {
        String id = cmbCusId.getValue();
        try {
            Customer customer = CustomerRepo.searchById(id);

            lblCustomerName.setText(customer.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {
        String id = cmbEmployeeId.getValue();
        try {
            Employee employee=EmployeeRepo.searchById(id);

            lblEmpName.setText(employee.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbFishIdOnAction(ActionEvent event) {
        String id = cmbFishId.getValue();
        try {
            Fish fish= FishRepo.searchById(id);

            if (fish != null) {

                lblName.setText(fish.getName());
                lblQtyOnHand.setText(fish.getQty());
                lblNormalPrice.setText(String.valueOf(fish.getNormalPrice()));
                lblWholeSalePrice.setText(String.valueOf(fish.getWholesaleprice()));
                cmbAccessorieId.getSelectionModel().clearSelection();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void searchBy(ActionEvent event) {

    }

    public void getCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = CustomerRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbCusId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
