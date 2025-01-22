package com.core;

import java.util.ArrayList;

import com.systems.ISystem;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Engine {
    public boolean isRunning;

    public EntityCreator entity_creator;
    public NodeCreator node_creator;
    public AnimationTimer GameLoop;
    
    private Initializer initializer;
    private ArrayList<ISystem> systems;

    public Engine() {
        this.systems = new ArrayList<ISystem>();
        this.entity_creator = new EntityCreator();
        this.node_creator = new NodeCreator();
        this.initializer = new Initializer(this);

        this.isRunning = false;
    }

    public void addSystem(ISystem system) {
        this.systems.add(system);
    }

    public Scene createStartScene(){
        Pane startRoot = new Pane();
        Button startButton = new Button("Start");
        startButton.setId("start-button");
        startButton.getStyleClass().add("button-style");
        startButton.setLayoutX(450);
        startButton.setLayoutY(450);

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/background/startbackground.png").toExternalForm(), 1000, 700, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        startRoot.setBackground(new Background(backgroundImage));
        startRoot.getChildren().add(startButton);
        Scene startScene = new Scene(startRoot, 1000, 700);
        return startScene;
    }

    public Scene createModePlayerScene(){
        Button twoPlayersButton = new Button("2 Players");
        Button threePlayersButton = new Button("3 Players");
        Button fourPlayersButton = new Button("4 Players");
        Button backButton = new Button("Back");

        twoPlayersButton.setId("player2-button");
        twoPlayersButton.getStyleClass().add("button-style");
        threePlayersButton.setId("player3-button");
        threePlayersButton.getStyleClass().add("button-style");
        fourPlayersButton.setId("player4-button");
        fourPlayersButton.getStyleClass().add("button-style");
        backButton.setId("back-button");
        backButton.getStyleClass().add("button-style");

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/background/startbackground.png").toExternalForm(), 1000, 700, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        VBox scene2Root = new VBox(20); // VBox with spacing of 20 between elements
        scene2Root.setBackground(new Background(backgroundImage));
        scene2Root.getChildren().addAll(twoPlayersButton, threePlayersButton, fourPlayersButton, backButton);
        scene2Root.setStyle("-fx-alignment: center;"); // Center everything in VBox
        Scene playersScene = new Scene(scene2Root, 1000, 700);
        return playersScene;
    }

    public Scene createMap() {
        StackPane root = new StackPane();
        BackgroundSize backgroundSize = new BackgroundSize(800, 800, false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/background/background7.jpg").toExternalForm(), 800, 800, false, true),
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
        for (int i=0; i<this.systems.size(); i++) {
            this.systems.get(i).update();
        }
    }
}