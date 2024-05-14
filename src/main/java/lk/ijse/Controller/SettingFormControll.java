package lk.ijse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class SettingFormControll {

    @FXML
    private JFXButton btnChangeEmail;

    @FXML
    private JFXButton btnChangeTheme;

    @FXML
    private JFXButton btnPassword;

    @FXML
    private JFXButton btnReports;

    @FXML
    private AnchorPane mainPain;

    @FXML
    private AnchorPane MainPain;


    @FXML
    void SettingOnMouseRelesed(MouseEvent event) {


    }

    @FXML
    void btnChangeEmailOnAction(ActionEvent event) {

    }

    @FXML
    void btnChangeThemeOnAction(ActionEvent event) throws IOException {



    }

    private void addHoverAnimation(JFXButton button) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), button);
        scaleIn.setFromX(1.0);
        scaleIn.setFromY(1.0);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), button);
        scaleOut.setFromX(1.1);
        scaleOut.setFromY(1.1);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        button.setOnMouseEntered(event -> {
            scaleIn.play();
        });

        button.setOnMouseExited(event -> {
            scaleOut.play();
        });
    }

    private void loadFormWithAtractiveAnimation(String formPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(formPath));
        AnchorPane newPane = loader.load();

        newPane.setOpacity(0);
        mainPain.getChildren().add(newPane);

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
            mainPain.getChildren().clear();
            mainPain.getChildren().add(newPane);
        });
        parallelTransition.play();
    }

    @FXML
    void btnPasswordOnAction(ActionEvent event) throws IOException {
        loadFormWithAtractiveAnimation("/view/changePassword.fxml");

    }


    @FXML
    void btnReportsOnAction(ActionEvent event) {

    }

}
