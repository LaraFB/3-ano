module com.example.gps_g11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    opens com.example.gps_g11 to javafx.fxml;
    exports com.example.gps_g11;
    exports com.example.gps_g11.Controller;
    opens com.example.gps_g11.Controller to javafx.fxml;
    exports com.example.gps_g11.Controller.Budget;
    opens com.example.gps_g11.Controller.Budget to javafx.fxml;
    exports com.example.gps_g11.Controller.Settings;
    opens com.example.gps_g11.Controller.Settings to javafx.fxml;
    exports com.example.gps_g11.Controller.Category;
    opens com.example.gps_g11.Controller.Category to javafx.fxml;
    exports com.example.gps_g11.Controller.Expenses;
    opens com.example.gps_g11.Controller.Expenses to javafx.fxml;
}