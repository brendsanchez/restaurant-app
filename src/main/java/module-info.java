module com.dusk.restaurant {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;

    requires org.kordamp.ikonli.javafx;
    requires java.logging;
    requires static lombok;

    opens com.dusk.restaurant to javafx.fxml;
    exports com.dusk.restaurant;
}