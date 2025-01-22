package com;

import com.core.Engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Engine engine = new Engine();
        Scene firstScene = engine.createMap();

        firstScene.getStylesheets().add(getClass().getResource("/button/main.css").toExternalForm());
        firstScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        
        engine.run();

        
        Scene startScene = engine.createStartScene();
        Scene playersScene = engine.createModePlayerScene();
        Button startButton = (Button) startScene.lookup("#start-button");
        Button backButton = (Button) playersScene.lookup("#back-button");
        startButton.setOnAction(e -> primaryStage.setScene(playersScene)); // Switch to Scene 2
        backButton.setOnAction(e -> primaryStage.setScene(startScene));  // Switch back to Scene 1

        
        startScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        playersScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        

        
        Scene startScene = engine.createStartScene();
        Scene playersScene = engine.createModePlayerScene();
        Button startButton = (Button) startScene.lookup("#start-button");
        Button backButton = (Button) playersScene.lookup("#back-button");
        startButton.setOnAction(e -> primaryStage.setScene(playersScene)); // Switch to Scene 2
        backButton.setOnAction(e -> primaryStage.setScene(startScene));  // Switch back to Scene 1

        
        startScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        playersScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        
        
        primaryStage.setTitle("Draft Map");
        primaryStage.setScene(startScene);
        primaryStage.setScene(startScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
