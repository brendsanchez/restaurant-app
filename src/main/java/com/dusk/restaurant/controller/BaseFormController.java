package com.dusk.restaurant.controller;

import com.dusk.restaurant.util.FXMLUtil;
import com.dusk.restaurant.util.ResourceUtil;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class BaseFormController {

    @FXML
    private StackPane rootPane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private AnchorPane formContainer;

    // Imagen cacheable para reutilización
    private static Image cachedImage;

    @FXML
    public void initialize() {
        if (cachedImage == null) {
            var image = ResourceUtil.getResourceFrom("/restaurant.jpg");
            cachedImage = new Image(image, true);
        }
        backgroundImage.setImage(cachedImage);

        // Ajustar al tamaño del StackPane
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
    }

    /**
     * Carga un formulario dinámicamente dentro del contenedor
     */
    public void loadForm(String fxmlForm) {
        Parent form = FXMLUtil.loadFXML(fxmlForm);
        formContainer.getChildren().clear();
        formContainer.getChildren().add(form);
    }
}
