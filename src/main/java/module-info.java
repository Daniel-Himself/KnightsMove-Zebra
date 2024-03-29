module com.knights_move {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jackson.mapper.asl;
    requires jackson.core.asl;
    requires java.desktop;
    requires java.scripting;


    exports com.knights_move.view;
    opens com.knights_move.view to javafx.fxml;
    exports com.knights_move.controller;
    opens com.knights_move.controller to javafx.fxml;
    exports com.knights_move;
    opens com.knights_move to javafx.fxml;
    exports com.knights_move.model;
    opens com.knights_move.model to javafx.fxml;
}