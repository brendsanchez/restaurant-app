package com.dusk.restaurant.controller;

import com.dusk.restaurant.service.LoginService;
import com.dusk.restaurant.util.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginController {

    private final LoginService loginService = new LoginService();

    @FXML
    private ImageView backgroundImage;

    @FXML
    private StackPane rootPane;

    @FXML
    private VBox loginContainer;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void initialize() {
        // Hacer que la imagen de fondo ocupe completo el StackPane
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());

        // Alinear el login a la izquierda del StackPane
        StackPane.setAlignment(loginContainer, Pos.CENTER_LEFT);
    }

    @FXML
    protected void goToRegister() {
        SceneManager.getInstance().setRoot("RegisterView");
    }

    @FXML
    protected void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        var alertDto = this.loginService.login(username, password);
        alertDto.getAlert().showAndWait();
    }
}