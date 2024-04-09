
package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.model.Fish;
import lk.ijse.model.Supplier;
import lk.ijse.model.tm.FishTm;
import lk.ijse.model.tm.SupplierTm;
import lk.ijse.repository.FishRepo;
import lk.ijse.repository.SupplierRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FishFormController {

    @FXML private TableColumn<?, ?> ColFishName;

    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private TableColumn<?, ?> colFishId;
    @FXML
    private TableColumn<?, ?> colNoramalPrice;
    @FXML
    private TableColumn<?, ?> colQtyOnHand;
    @FXML
    private TableColumn<?, ?> colWholeSalePrice;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private TableView<FishTm> tblFish;
    @FXML
    private TextField txtFishId;
    @FXML
    private TextField txtFishName;
    @FXML
    private TextField txtQtyOnHand;
    @FXML
    private TextField txtNormalPrice;
    @FXML
    private TextField txtWholeSalePrice;

    public void initialize() throws IOException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllCustomers();
        getCurrentOrderId();

    }
    private void getCurrentOrderId() {
        try {
            String currentId = FishRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtFishId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("0");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "F0" + ++idNum;
        }
        return "F01";
    }

    private void loadAllCustomers() {
        ObservableList<FishTm> obList = FXCollections.observableArrayList();

        try {
            List<Fish> fishList = FishRepo.getAll();
            for (Fish fish : fishList) {
                FishTm tm = new FishTm(
                       fish.getId(),
                       fish.getName(),
                       fish.getQty(),
                       fish.getNormalPrice(),
                       fish.getWholesaleprice()
                );

                obList.add(tm);
            }

            tblFish.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colFishId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColFishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colNoramalPrice.setCellValueFactory(new PropertyValueFactory<>("normalPrice"));
        colWholeSalePrice.setCellValueFactory(new PropertyValueFactory<>("wholesaleprice"));


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
    void btnClearOnAction(ActionEvent event) {
        txtFishId.setText("");
        txtFishName.setText("");
        txtQtyOnHand.setText("");
        txtNormalPrice.setText("");
        txtWholeSalePrice.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtFishId.getText();

        try {
            boolean isDeleted = FishRepo.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fish deleted!").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id=txtFishId.getText();
        String name = txtFishName.getText();
        String qty = txtQtyOnHand.getText();
        String normalPrice = txtNormalPrice.getText();
        String wholeSalePrice = txtWholeSalePrice.getText();


        Fish fish = new Fish(id, name, qty,normalPrice,wholeSalePrice);


        try {
            boolean isSaved = FishRepo.save(fish);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!!!.").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
                getCurrentOrderId();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtFishId.setText("");
        txtFishName.setText("");
        txtQtyOnHand.setText("");
        txtNormalPrice.setText("");
        txtWholeSalePrice.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id=txtFishId.getText();
        String name = txtFishName.getText();
        String qty = txtQtyOnHand.getText();
        String normalPrice = txtNormalPrice.getText();
        String wholeSalePrice = txtWholeSalePrice.getText();


        Fish fish = new Fish(id, name, qty,normalPrice,wholeSalePrice);

        try {
            boolean isUpdated = FishRepo.update(fish);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Fish updated!").show();
                clearFields();
                setCellValueFactory();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtFishId.getText();

      Fish fish = FishRepo.searchById(id);
        if (fish != null) {
            txtFishId.setText(fish.getId());
            txtFishName.setText(fish.getName());
            txtQtyOnHand.setText(fish.getQty());
            txtNormalPrice.setText(fish.getNormalPrice());
            txtWholeSalePrice.setText(fish.getWholesaleprice());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
        }
    }



}
