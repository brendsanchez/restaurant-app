module com.dusk.restaurant {
    requires static lombok;

    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    requires org.kordamp.ikonli.javafx;
    requires java.logging;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    exports com.dusk.restaurant;
    exports com.dusk.restaurant.controller;
    exports com.dusk.restaurant.dto;

    opens com.dusk.restaurant to javafx.fxml;
    opens com.dusk.restaurant.controller to javafx.fxml;
    exports com.dusk.restaurant.util;
    exports com.dusk.restaurant.client;
    exports com.dusk.restaurant.client.dto;
}