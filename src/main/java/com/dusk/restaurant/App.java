package com.dusk.restaurant;

import com.dusk.restaurant.util.SceneManager;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.getInstance().init(stage, "LoginView");
    }

    public static void main(String[] args) {
        launch();
    }
}