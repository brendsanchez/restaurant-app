package com.dusk.restaurant.util;

import com.dusk.restaurant.App;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SceneManager {

    private static SceneManager instance;
    private Stage primaryStage;
    private Scene scene;

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    // Inicializar el Stage principal (una vez al arrancar la app)
    public void init(Stage stage, String fxml) throws IOException {
        this.primaryStage = stage;
        Parent root = FXMLUtils.loadFXML(fxml);
        this.scene = new Scene(root);

        this.addStyles();

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void addStyles() {
        var indexCss = Objects.requireNonNull(App.class.getResource("css/index.css"));
        this.scene.getStylesheets().add(indexCss.toExternalForm());
    }

    // Cambiar la raíz de la escena
    public void setRoot(String fxml) {
        scene.setRoot(FXMLUtils.loadFXML(fxml));
    }

}
