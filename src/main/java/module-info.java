module com.daniel.conversor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.daniel.conversor to javafx.fxml;
    exports com.daniel.conversor;
    exports com.daniel.conversor.controller;
    opens com.daniel.conversor.controller to javafx.fxml;
}