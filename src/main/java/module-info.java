module com.example.weatherappjane {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;
    exports com.example.weatherappjane;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.weatherappjane to javafx.fxml;
}