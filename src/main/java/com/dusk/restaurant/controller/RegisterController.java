package com.dusk.restaurant.controller;

import com.dusk.restaurant.util.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.synedra.validatorfx.Validator;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    private Validator validator;


    @FXML
    private ImageView backgroundImage;

    @FXML
    private StackPane rootPane;

    @FXML
    private VBox registerContainer;

    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());

        // Alinear el login a la izquierda del StackPane
        StackPane.setAlignment(registerContainer, Pos.CENTER_LEFT);

        validator = new Validator();

        // Validación de usuario
        validator.createCheck()
                .dependsOn("username", txtUsername.textProperty())
                .withMethod(c -> {
                    String value = c.get("username");
                    if (value == null || value.trim().isEmpty()) {
                        c.error("El usuario no puede estar vacío");
                    } else if (value.trim().length() < 3) {
                        c.error("El usuario debe tener al menos 3 caracteres");
                    }
                })
                .decorates(txtUsername);

        // Validación de contraseña
        validator.createCheck()
                .dependsOn("password", txtPassword.textProperty())
                .withMethod(c -> {
                    String value = c.get("password");
                    if (value == null || value.trim().isEmpty()) {
                        c.error("La contraseña no puede estar vacía");
                    } else if (value.trim().length() < 6) {
                        c.error("La contraseña debe tener al menos 6 caracteres");
                    }
                })
                .decorates(txtPassword);
    }

    @FXML
    private void handleRegister() throws IOException {
        // Ejecutar la validación
        if (validator.validate()) {
            // Si pasa la validación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Registro exitoso");
            alert.setContentText("Usuario registrado correctamente");
            alert.showAndWait();

            goToLogin();
        }
    }

    @FXML
    protected void goToLogin() throws IOException {
        SceneManager.getInstance().setRoot("LoginView");
    }
}
