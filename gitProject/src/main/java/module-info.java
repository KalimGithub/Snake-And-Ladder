module com.example.snakeandladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeandladder to javafx.fxml;
    exports com.example.snakeandladder;
}