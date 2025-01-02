package com.core;

import java.util.ArrayList;

import com.systems.ISystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Engine {
    public boolean isRunning;

    public EntityCreator entity_creator;
    public NodeCreator node_creator;
    
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
        GridPane map = this.initializer.initMap();
        Scene firstScene = new Scene(map, Settings.WIDTH, Settings.HEIGHT);

        return firstScene;
    }

    public void run() {
        this.isRunning = true;

		long lastime = System.nanoTime();
        double FPS = Settings.FPS;
		double ns = 1000000000 / FPS;
		double delta = 0;
		int frames = 0;
		double time = System.currentTimeMillis();

        double stop_time = time + 1000 * 5;
		
		while(isRunning == true) {
            if (System.currentTimeMillis()>stop_time) {
                isRunning = false;
            }

			long now = System.nanoTime();
			delta += (now - lastime) / ns;
			lastime = now;
			
			if(delta >= 1) {
				this.update();
				
				frames++;
				delta--;
				if(System.currentTimeMillis() - time >= 1000) {
					System.out.println("fps:" + frames);
					time += 1000;
					frames = 0;
				}
			}
		}
	}

    private void update() {
        for (int i=0; i<this.systems.size(); i++) {
            this.systems.get(i).update();
        }
    }
}
