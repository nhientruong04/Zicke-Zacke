package com.core;

import java.util.ArrayList;

import com.systems.System;

public class Engine {
    public EntityCreator entity_creator;
    public NodeCreator node_creator;

    private ArrayList<System> systems;

    public Engine() {
        this.systems = new ArrayList<System>();
        this.entity_creator = new EntityCreator();
    }

    public void addSystem(System system) {
        this.systems.add(system);
    }

    public void start() {
        
    }
}
