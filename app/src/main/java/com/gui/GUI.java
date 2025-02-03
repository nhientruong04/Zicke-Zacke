package com.gui;

import java.util.ArrayList;
import java.util.List;

import com.core.Settings;
import com.core.Utils;

import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GUI {
    public Stage primaryStage;

    public GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene createWinScene(int winner_id) {
        StackPane root = new StackPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);

        ImageView winner = new ImageView(new Image(getClass().getResource("/chicken/chicken_" + winner_id + "/1.png").toExternalForm()));
        winner.fitWidthProperty().bind(root.maxWidthProperty().divide(300).multiply(Settings.CHICKEN_WIDTH_BASE));
        winner.setPreserveRatio(true);

        List<Image> frames = new ArrayList<Image>();

        frames.add(new Image(getClass().getResource("/chicken/chicken_" + winner_id + "/1.png").toExternalForm()));
        frames.add(new Image(getClass().getResource("/chicken/chicken_" + winner_id + "/2.png").toExternalForm()));
        frames.add(new Image(getClass().getResource("/chicken/chicken_" + winner_id + "/3.png").toExternalForm()));
        frames.add(new Image(getClass().getResource("/chicken/chicken_" + winner_id + "/4.png").toExternalForm()));

        Timeline sprite_timeLine = Utils.createAnimation(winner, frames);

        Button menuButton = new Button("Menu");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(value -> {
            System.exit(0);
        });
        // Images for alternating
        Image image1 = new Image(getClass().getResource("/victoryAnimation/victory_down.png").toExternalForm());
        ImageView winningImage = new ImageView(image1);
        winningImage.setId("victory-image1");
        winningImage.setPreserveRatio(true);
        winningImage.setFitHeight(150);

        // StackPane to hold images
        StackPane imageStackPane = new StackPane();
        imageStackPane.getChildren().add(winningImage); // Initially add image1

        menuButton.getStyleClass().add("menu-exit-button-style");
        exitButton.getStyleClass().add("menu-exit-button-style");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(winner);
        borderPane.setTop(imageStackPane);
        borderPane.setLeft(menuButton);
        borderPane.setRight(exitButton);
        BorderPane.setMargin(menuButton, new Insets(100, 0, 0, 30));
        BorderPane.setMargin(exitButton, new Insets(100, 30, 0, 0));

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true,
                true);
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/background/background8.jpg").toExternalForm(), 1000, 700, false,
                        true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(backgroundImage));

        root.getChildren().addAll(borderPane);
        Scene winningScene = new Scene(root, 1000, 700);

        winningScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        sprite_timeLine.play();

        return winningScene;
    }

    

    public void setScene(Scene scene) {
        this.primaryStage.setScene(scene);
    }
}
