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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.model.Fish;
import lk.ijse.model.SupFish;
import lk.ijse.model.Supplier;
import lk.ijse.model.tm.SupFishTm;
import lk.ijse.repository.FishRepo;
import lk.ijse.repository.SupFishRepo;
import lk.ijse.repository.SupplierRepo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddNewQtyController {

    public AnchorPane mainPane;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXComboBox<String> cmbFishId;

    @FXML
    private JFXComboBox<String> cmbSup;

    @FXML
    private TableColumn<?, ?> colFishId2;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblFishId;

    @FXML
    private Label lblSup;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<SupFishTm> tblAccSupFIsh;

    @FXML
    private TextField txtQtyy;

    public void initialize() throws IOException, SQLException {
        setDate();
        setTime();
        setCellValueFactory();
        loadAllSupFIsh();
        getSupplierIds();
        getFishId();

    }

    private void setCellValueFactory() {
        colFishId2.setCellValueFactory(new PropertyValueFactory<>("FishId"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));



    }
    private void loadAllSupFIsh() throws SQLException {
        ObservableList<SupFishTm> obList2 = FXCollections.observableArrayList();

        try {


            List<SupFish> supFishList = SupFishRepo.getAll();
            for (SupFish supFish : supFishList) {

                SupFishTm tm1 = new SupFishTm(
                        supFish.getFisId(),
                        supFish.getSupId(),
                        supFish.getDate(),
                        supFish.getQty()
                );
                obList2.add(tm1);

            }
            tblAccSupFIsh.setItems(obList2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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




    private void getSupplierIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = SupplierRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            cmbSup.setItems(obList);

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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String supId= (String) cmbSup.getValue();
        String fishId= (String) cmbFishId.getValue();
        int qty = Integer.parseInt(txtQtyy.getText());
        Date date = Date.valueOf(LocalDate.now());
        System.out.println(fishId + qty);

        SupFish supFish = new SupFish(fishId, supId, date, qty);

        try {
            boolean isUpdate = FishRepo.updateSupFish(qty, fishId);
            if (isUpdate) {
                boolean isSaved = SupFishRepo.save(supFish);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Qty Is Updated").show();
                    loadAllSupFIsh();
                }

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbFishIdOnAction(ActionEvent event) {
        String id = cmbFishId.getValue();
        try {
            Fish fish= FishRepo.searchById(id);

            if (fish != null) {

                lblFishId.setText(fish.getName());

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbSupOnAction(ActionEvent event) {
        String id = cmbSup.getValue();
        try {
            Supplier supplier = SupplierRepo.searchById(id);

            lblSup.setText(supplier.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
