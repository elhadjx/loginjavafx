package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView showPasswordIV;

    private boolean showPasswordState = false;

    @FXML
    public TextField textPasswordField; // Used to show the password in plain text


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        // Create a TextField that will replace PasswordField when needed
        textPasswordField.setVisible(false);
        textPasswordField.setManaged(false);
        textPasswordField.setMaxWidth(0);

        // Sync password between both fields
        textPasswordField.textProperty().bindBidirectional(passwordField.textProperty());
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String pass  = passwordField.getText();

        if (!isEmailValid(email)){
            // invalid email
            System.out.println("Invalid email");
            statusLabel.setText("Invalid Email, use firstname.lastname@email.com format!");
            emailField.requestFocus();
            return;
        }

        User user = new User(email);
        System.out.println(user);

        if (!user.isLoginValid(pass)){
            // invalid password
            System.out.println("Incorrect password");
            statusLabel.setText("Incorrect password, Please try again!");
            passwordField.requestFocus();
            return;
        }

        System.out.println( "Welcome Back " + user.getFirstname() + "! Logging you in...");
        statusLabel.setText("Welcome Back " + user.getFirstname() + "! Logging you in...");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo2/dashboard.fxml"));
        Parent root = loader.load();

        DashboardController dashboardController = loader.getController();
        dashboardController.setUser(user);

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Save current width and height
        double width = stage.getWidth();
        double height = stage.getHeight();

        // Set new scene
        Scene newScene = new Scene(root);
        newScene.setFill(Color.TRANSPARENT); // Make the scene background transparent
        stage.setScene(newScene);



        // Apply the same size
        stage.setWidth(width);
        stage.setHeight(height);

        stage.setTitle("Dashboard");
        stage.show();

    }

    boolean isEmailValid(String email){
        String EMAIL_REGEX = "^[a-zA-Z]+\\.[a-zA-Z]+@[a-zA-Z]+\\.com$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @FXML
    void switchFocusToPassword(ActionEvent event) {
        passwordField.requestFocus();
    }

    @FXML
    void switchShowPassword(MouseEvent event){
        showPasswordState = !showPasswordState;
        if (showPasswordState) {
            // Hide password
            textPasswordField.setMaxWidth(0);
            passwordField.setMaxWidth(300);
            textPasswordField.setVisible(false);
            textPasswordField.setManaged(false);
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            showPasswordIV.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/show.png"))));
        } else {
            // Show password
            textPasswordField.setMaxWidth(300);
            passwordField.setMaxWidth(0);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
            textPasswordField.setVisible(true);
            textPasswordField.setManaged(true);
            showPasswordIV.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/media/hide.png"))));
        }

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

