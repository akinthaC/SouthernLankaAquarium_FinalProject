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
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.*;

import lk.ijse.model.tm.cartTm;
import lk.ijse.repository.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    private JFXComboBox<String> cmbStatus;

    @FXML
    private TableColumn<?, ?> colActive;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colHandOverDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private TableColumn<?, ?> colTotal;

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
    private Label lblType;

    @FXML
    private Label lblWholeSalePrice;

    @FXML
    private TableView<cartTm> tblPlaceOrder;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtHandOverDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    private ObservableList<cartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        setDate();
        setTime();
        getCurrentOrderId();
        getCustomerIds();
        getEmployeeId();
        getFishId();
        getAccessoriesId();
        getStatus();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colHandOverDate.setCellValueFactory(new PropertyValueFactory<>("handOver"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

    }

    private void getStatus() {

        ObservableList<String> obList = FXCollections.observableArrayList();


        List<String> idList = OrderRepo.getStatus();

        for(String value : idList) {
            obList.add(value);
        }

        cmbStatus.setItems(obList);


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
        txtDate.setText(String.valueOf(now));
    }

    @FXML
    void brnAddTOCartOnAction(ActionEvent event) {
        String code = txtId.getText();
        Date date = Date.valueOf(txtDate.getText());
        Date handOverDate = Date.valueOf(txtHandOverDate.getText());
        int qty = Integer.parseInt(txtQty.getText());
        String status = cmbStatus.getValue();
        String description=lblName.getText();
        double price;
        String id;

        if (lblType.getText().equals("normal")) {
             price = Double.parseDouble(lblNormalPrice.getText());
            System.out.println("price = " + price);

        }else {
             price = Double.parseDouble(lblWholeSalePrice.getText());
            System.out.println("price = " + price);
        }

        if ( cmbAccessorieId.getValue()== null) {
            id = cmbFishId.getValue();
        }else {
            id= cmbAccessorieId.getValue();
        }

        double total = qty * price;

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);
        btnRemove.setStyle("-fx-background-color: #e32323; -fx-text-fill: white; " +
                "-fx-background-radius: 30; -fx-shape: 'M 0 20 Q 0 0 20 0 L 80 0 Q 100 0 100 20 L 100 80 Q 100 100 80 100 L 20 100 Q 0 100 0 80 L 0 20 Z'; " +
                "-fx-border-color: black; -fx-border-width: 2;");

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblPlaceOrder.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblPlaceOrder.refresh();
               // calculateNetTotal();
            }
        });

        for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
            if(code.equals(colId.getCellData(i))) {

                cartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * price;

                tm.setQty(qty);
                tm.setTotal(total);

                tblPlaceOrder.refresh();

                //calculateNetTotal();
                return;
            }
        }

        cartTm cartTm = new cartTm(code, date, handOverDate, description, qty, price, total, status,id, btnRemove);
        obList.add(cartTm);

        tblPlaceOrder.setItems(obList);



    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = txtId.getText();
        Date date = Date.valueOf(txtDate.getText());
        Date handOverDate = Date.valueOf(txtHandOverDate.getText());
        int qty = (int) Double.parseDouble(txtQty.getText());
        String status = cmbStatus.getValue();
        String cusId = cmbCusId.getValue();
        String description = lblName.getText();
        String empId = cmbEmployeeId.getValue();
        String fishId = null;
        String accessoriesId= null;
        char s1='F';


        Order order = new Order(orderId, date, handOverDate, qty, cusId);
        List<OrderDetail> odList = new ArrayList<>();

        for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
            cartTm tm = obList.get(i);

            boolean isEquals= checkEquals(tm.getId(),s1);

            if (isEquals) {
                fishId=tm.getId();
                accessoriesId=null;


            }else{
                accessoriesId=tm.getId();
                fishId=null;

            }


            OrderDetail od = new OrderDetail(
                    orderId,
                    empId,
                    fishId,
                    accessoriesId,
                    tm.getQty(),
                    tm.getStatus(),
                    tm.getDescription(),
                    date

            );

            odList.add(od);
            System.out.println("od = " + od);
        }
        PlaceOrder pl= new PlaceOrder(order,odList);

        try {
            boolean isPlaced = PlaceOrderRepo.orders(pl);
            txtQty.setText("");
            if (isPlaced) {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private boolean checkEquals(String id, char s1) {
        for (int i = 0; i < id.length(); i++) {
            char ch = id.charAt(i);
            if (s1 == (ch)) {
                System.out.println("ch = " + ch);
                return true;

            }else{
                System.out.println("fuck = " + ch);
                return false;
            }
        }
        return false;
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
            lblType.setText(customer.getType());

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

    @FXML
    void cmbStatusOnAction(ActionEvent event) {



    }
}
