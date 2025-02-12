package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardController {

    User user;

    @FXML
    Label welcomeLabel;


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
    }

    public void setUser(User in_user) {
        user = in_user;

        welcomeLabel.setText("Welcome, " + user.getFirstname() + "!");
        System.out.println("Welcome, " + user.getFirstname() + "!");
    }

    @FXML
    private void minimizeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true); // Minimize window
    }

    @FXML
    private void maximizeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage.isMaximized()) {
            stage.setMaximized(false); // Restore window
        } else {
            stage.setMaximized(true); // Maximize window
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Close window
    }

    @FXML
    private void onMousePressed(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
}
