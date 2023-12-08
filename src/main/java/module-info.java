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
    opens com.example.gps_g11.Data.Transacao to javafx.base;
    exports com.example.gps_g11;
    exports com.example.gps_g11.Controller;
    opens com.example.gps_g11.Controller to javafx.fxml;
    exports com.example.gps_g11.Controller.Historico;
    opens com.example.gps_g11.Controller.Historico to javafx.fxml;

    exports com.example.gps_g11.Controller.Envelope;
    opens com.example.gps_g11.Controller.Envelope to javafx.fxml;
    exports com.example.gps_g11.Controller.Home;
    opens com.example.gps_g11.Controller.Home to javafx.fxml;
    opens com.example.gps_g11.Controller.Objetivo to javafx.fxml;
    opens com.example.gps_g11.Controller.Estatistica to javafx.fxml;
}