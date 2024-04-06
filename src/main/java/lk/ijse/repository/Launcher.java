package lk.ijse.repository;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.ijse.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(FXMLLoader
                .load(this.getClass().getResource("/view/Login_form.fxml"))));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();


    }
}
