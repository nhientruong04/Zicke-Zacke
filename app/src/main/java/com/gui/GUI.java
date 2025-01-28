package com.gui;

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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GUI {
    public Stage primaryStage;

    public GUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene createWinScene() {
        StackPane root = new StackPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);

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
        borderPane.setTop(imageStackPane);
        borderPane.setLeft(menuButton);
        borderPane.setRight(exitButton);
        BorderPane.setMargin(menuButton, new Insets(100, 0, 0, 30));
        BorderPane.setMargin(exitButton, new Insets(100, 30, 0, 0));

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true,
                true);
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/background/background1.jpg").toExternalForm(), 1000, 700, false,
                        true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(backgroundImage));

        root.getChildren().add(borderPane);
        Scene winningScene = new Scene(root, 1000, 700);

        winningScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        return winningScene;
    }
}
