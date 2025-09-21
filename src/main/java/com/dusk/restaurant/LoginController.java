package com.dusk.restaurant;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {

    @FXML
    private ImageView backgroundImage;

    @FXML
    private StackPane rootPane;

    @FXML
    private VBox loginContainer;

    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        // Hacer que la imagen de fondo ocupe completo el StackPane
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());

        // Alinear el login a la izquierda del StackPane
        StackPane.setAlignment(loginContainer, Pos.CENTER_LEFT);
    }

    @FXML
    protected void handleLogin() {
        welcomeText.setText("go to site");
    }

    @FXML
    protected void goToRegister() throws IOException {
        App.setRoot("RegisterView");
    }
}