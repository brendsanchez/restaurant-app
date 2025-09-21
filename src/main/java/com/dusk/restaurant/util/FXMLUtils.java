package com.dusk.restaurant.util;

import com.dusk.restaurant.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLUtils {

    public static Parent loadFXML(String fxml) throws IOException {
        return getFXMLLoader(fxml).load();
    }

    public static FXMLLoader getFXMLLoader(String fxml) {
        var fileName = "fxml/" + fxml + ".fxml";
        return new FXMLLoader(App.class.getResource(fileName));
    }
}
