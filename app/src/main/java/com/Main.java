package com;

import com.core.Engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Engine engine = new Engine();
        Scene firstScene = engine.createMap();

        firstScene.getStylesheets().add(getClass().getResource("/button/main.css").toExternalForm());
        firstScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        
        engine.run();

        primaryStage.setTitle("Draft Map");
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
