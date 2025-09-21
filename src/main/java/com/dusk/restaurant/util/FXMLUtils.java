package com.dusk.restaurant.util;

import com.dusk.restaurant.App;
import com.dusk.restaurant.exception.GenericException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLUtils {

    public static Parent loadFXML(String fxml) {
        try {
            return getFXMLLoader(fxml).load();
        } catch (IOException e) {
            throw new GenericException("not found fxml: " + fxml);
        }
    }

    public static FXMLLoader getFXMLLoader(String fxml) {
        var fileName = "fxml/" + fxml + ".fxml";
        return new FXMLLoader(App.class.getResource(fileName));
    }
}
