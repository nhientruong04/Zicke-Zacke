package com.core;

import java.util.ArrayList;

import com.systems.ISystem;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

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

    public Scene createMap() {
        StackPane root = new StackPane();
        BackgroundSize backgroundSize = new BackgroundSize(800, 800, false, false, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource("/background/background6.png").toExternalForm(), 800, 800, false, true),
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