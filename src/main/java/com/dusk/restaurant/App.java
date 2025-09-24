package com.dusk.restaurant;

import com.dusk.restaurant.util.SceneManager;
import com.dusk.restaurant.util.SessionManager;
import javafx.stage.Stage;

public class App extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        if (SessionManager.restoreSession()) {
            SceneManager.getInstance().init(stage, "MainView");
        } else {
            SceneManager.getInstance().init(stage, "LoginView");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}