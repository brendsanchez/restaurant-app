package com.dusk.restaurant;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void handleLogin() {
        welcomeText.setText("go to site");
    }

    @FXML
    protected void goToRegister() throws IOException {
        App.setRoot("RegisterView");
    }
}