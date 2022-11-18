module com.knights_move {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.knights_move to javafx.fxml;
    exports com.knights_move;
}