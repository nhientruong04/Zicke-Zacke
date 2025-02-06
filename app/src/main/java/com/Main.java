package com;

import com.core.Engine;
import com.gui.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {

    private MediaPlayer background_music;

    @Override
    public void start(Stage primaryStage) {
        GUI gui = new GUI(primaryStage);
        Engine engine = new Engine(gui);
        
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
        primaryStage.show();

        try {
            this.background_music = new MediaPlayer(new Media(getClass().getResource("/sound/background_music.wav").toExternalForm()));

            background_music.setCycleCount(MediaPlayer.INDEFINITE);
            background_music.setVolume(0.5);
            background_music.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
