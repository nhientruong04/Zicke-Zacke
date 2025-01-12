package com;

import com.core.Engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        // root.getStyleClass().add("image-background"); // Apply the CSS class
        // ImageView background = new ImageView(new Image(getClass().getResource("/background/background1.jpg").toExternalForm()));
        // background.fitWidthProperty().bind(primaryStage.widthProperty());
        // background.fitHeightProperty().bind(primaryStage.heightProperty());
        // background.setPreserveRatio(false); // Stretch to fill the scene
        // Add the background to the root pane
        // root.getChildren().add(background);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/background/background1.jpg").toExternalForm(), 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));
        

        Engine engine = new Engine();
        Scene firstScene = engine.createMap();

        firstScene.getStylesheets().add(getClass().getResource("/button/main.css").toExternalForm());
        firstScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        
        engine.run();

        // Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Draft Map");
        primaryStage.setScene(firstScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
