package com.core;

import java.util.ArrayList;
import java.util.Stack;

import com.gui.GUI;
import com.systems.ISystem;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class Engine {
    public boolean isRunning;

    public EntityCreator entity_creator;
    public NodeCreator node_creator;
    public AnimationTimer GameLoop;
    public GUI gui;

    private Initializer initializer;
    private ArrayList<ISystem> systems;

    public Engine(GUI gui) {
        this.systems = new ArrayList<ISystem>();
        this.entity_creator = new EntityCreator();
        this.node_creator = new NodeCreator();
        this.initializer = new Initializer(this);
        this.gui = gui;

        this.isRunning = false;
    }

    public void addSystem(ISystem system) {
        this.systems.add(system);
    }

    public Scene createStartScene() {
        StackPane root = new StackPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        GridPane buttonGridPane = new GridPane();
        buttonGridPane.setId("start-exit-button-gridpane");
        buttonGridPane.setVgap(10);
        buttonGridPane.setAlignment(javafx.geometry.Pos.CENTER);
        Button startButton = new Button("Start");
        startButton.setId("start-button");
        startButton.getStyleClass().add("start-exit-button-style");

        Button exitButton = new Button("Exit");
        exitButton.setId("exit-button");
        exitButton.getStyleClass().add("start-exit-button-style");
        exitButton.setOnAction(value -> {
            System.exit(0);
        });

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true,
                true);
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/background/startbackground.png").toExternalForm(), 1000, 700, false,
                        true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(backgroundImage));

        Button instructionButton = new Button();
        instructionButton.setId("instruction-button");
        instructionButton.getStyleClass().add("instruction-button-style");
        Image instructionImage = new Image(getClass().getResource("/background/question.png").toExternalForm());
        ImageView instructionImageView = new ImageView(instructionImage);
        instructionButton.setGraphic(instructionImageView);
        instructionImageView.setFitWidth(50); // Set the desired width
        instructionImageView.setFitHeight(50); // Set the desired height

        StackPane.setAlignment(instructionButton, javafx.geometry.Pos.TOP_RIGHT);
        StackPane.setMargin(instructionButton, new Insets(20, 20, 0, 0));

        buttonGridPane.add(startButton, 1, 0);
        buttonGridPane.add(exitButton, 1, 1);
        root.getChildren().addAll(buttonGridPane, instructionButton);
        buttonGridPane.setTranslateY(120);
        Scene startScene = new Scene(root, 1000, 700);
        return startScene;
    }

    public Scene createModePlayerScene() {

        StackPane root = new StackPane();
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Button twoPlayersButton = new Button("2 Players");
        Button threePlayersButton = new Button("3 Players");
        Button fourPlayersButton = new Button("4 Players");
        Button backButton = new Button("Back");

        // Set IDs and styles for buttons
        twoPlayersButton.setId("player2-button");
        twoPlayersButton.getStyleClass().add("player-button-style");
        threePlayersButton.setId("player3-button");
        threePlayersButton.getStyleClass().add("player-button-style");
        fourPlayersButton.setId("player4-button");
        fourPlayersButton.getStyleClass().add("player-button-style");
        backButton.setId("back-button");
        backButton.getStyleClass().add("back-button-style");

        // Create a GridPane to hold the first three buttons
        GridPane buttonTable = new GridPane();
        buttonTable.setId("button-table");
        buttonTable.setVgap(10); // Horizontal gap between elements
        buttonTable.setAlignment(javafx.geometry.Pos.CENTER); // Center the GridPane

        // Add buttons to the GridPane
        buttonTable.add(twoPlayersButton, 1, 0); // Column 1, Row 0
        buttonTable.add(threePlayersButton, 1, 1); // Column 1, Row 1
        buttonTable.add(fourPlayersButton, 1, 2); // Column 1, Row 2

        GridPane backButtonTable = new GridPane();
        backButtonTable.setId("button-table");
        backButtonTable.setVgap(10); // Horizontal gap between elements
        backButtonTable.setAlignment(javafx.geometry.Pos.CENTER); // Center the GridPane

        // Add buttons to the GridPane
        backButtonTable.add(backButton, 1, 0); // Column 1, Row 0

        // Background image settings
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true,
                true);
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/background/startbackground.png").toExternalForm(), 1000, 700, false,
                        true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(backgroundImage));

        // VBox as root container
        VBox buttonBox = new VBox(20); // VBox with spacing of 20 between elements
        buttonBox.getChildren().addAll(buttonTable, backButtonTable); // Add the button table and the back button
        root.getChildren().add(buttonBox); // Add the VBox to the root StackPane
        buttonBox.setTranslateY(100); // Move the VBox down by 100 pixels
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER); // Center the VBox
        // Create the scene
        Scene playersScene = new Scene(root, 1000, 700);
        return playersScene;
    }

    // public Scene createInstructionScene(){
    //     StackPane root = new StackPane();   
    //     root.setAlignment(javafx.geometry.Pos.CENTER);


    // }

    public Scene createMap() {
        StackPane root = new StackPane();
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true,
                true);
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/background/background8.jpg").toExternalForm(), 1000, 700, false,
                        true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(backgroundImage));
        Scene firstScene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);

        this.initializer.initGame(root);

        return firstScene;
    }

    public void run() {
        this.GameLoop = new AnimationTimer() {
            public Engine outer() {
                return Engine.this;
            }

            @Override
            public void handle(long arg0) {
                outer().update();
            }
        };

        this.GameLoop.start();
    }

    public void stop() {
        this.GameLoop.stop();
    }

    private void update() {
        for (int i = 0; i < this.systems.size(); i++) {
            this.systems.get(i).update();
        }
    }
}