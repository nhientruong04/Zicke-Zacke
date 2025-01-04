package com.core;

import java.util.ArrayList;

import com.systems.ISystem;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
        GridPane map = this.initializer.initGame();
        Scene firstScene = new Scene(map, Settings.WIDTH, Settings.HEIGHT);

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