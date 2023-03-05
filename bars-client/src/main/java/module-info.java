module com.loschakov.barsclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires jackson.databind;
    requires jackson.core;
    requires javafx.graphics;


    opens com.loschakov.barsclient to javafx.fxml;
    exports com.loschakov.barsclient;
}