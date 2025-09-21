package com.dusk.restaurant;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends javafx.application.Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("LoginView"), 320, 240);
        addStyles();

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    static void addStyles() {
        var internalStyles = Objects.requireNonNull(App.class.getResource("css/index.css"))
                .toExternalForm();

        scene.getStylesheets().add(internalStyles);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        var fileName = "fxml/" + fxml + ".fxml";
        var fxmlLoader = new FXMLLoader(App.class.getResource(fileName));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}