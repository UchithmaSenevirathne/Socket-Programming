package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ServerInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.
                load(this.getClass().getResource("/view/Server.fxml"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
