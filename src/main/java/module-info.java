module com.knights_move {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jackson.mapper.asl;
    requires jackson.core.asl;


    exports com.knights_move.view;
    opens com.knights_move.view to javafx.fxml;
    exports com.knights_move.controller;
    opens com.knights_move.controller to javafx.fxml;
    exports com.knights_move.ui_controllers;
    opens com.knights_move.ui_controllers to javafx.fxml;
    exports com.knights_move;
    opens com.knights_move to javafx.fxml;
}