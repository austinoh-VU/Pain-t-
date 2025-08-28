module com.pain.t {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.swing;


    opens com.pain.t to javafx.fxml;
    exports com.pain.t;
}