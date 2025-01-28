package com;

import com.core.Engine;
import com.gui.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GUI gui = new GUI(primaryStage);
        Engine engine = new Engine(gui);
        Scene firstScene = engine.createMap();

        firstScene.getStylesheets().add(getClass().getResource("/button/main.css").toExternalForm());
        firstScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        
        engine.run();

        
        Scene startScene = engine.createStartScene();
        Scene playersScene = engine.createModePlayerScene();
        Scene winningScene = gui.createWinScene();
        Button startButton = (Button) startScene.lookup("#start-button");
        Button backButton = (Button) playersScene.lookup("#back-button");
        startButton.setOnAction(e -> primaryStage.setScene(playersScene)); // Switch to Scene 2
        backButton.setOnAction(e -> primaryStage.setScene(startScene));  // Switch back to Scene 1

        
        startScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        playersScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        
        primaryStage.setTitle("Draft Map");
        primaryStage.setScene(firstScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
