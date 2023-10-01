module proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens proyecto.clases to javafx.fxml;
    exports proyecto.clases;
}
