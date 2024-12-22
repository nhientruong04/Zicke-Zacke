package com.systems;

import com.nodes.Node;

import java.util.ArrayList;

public class RenderSystem extends System {

    private ArrayList<Node> nodes;

    public RenderSystem() {
        this.nodes = new ArrayList<Node>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    @Override
    public void update() {
        for (Node node : this.nodes) {
            int x = 1+1;
        }
    }
}
