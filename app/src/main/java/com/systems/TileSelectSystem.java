package com.systems;

import java.util.ArrayList;

import com.nodes.RenderNode;

public class TileSelectSystem extends ISystem {
    private ArrayList<RenderNode> nodes;

    public TileSelectSystem() {
        this.nodes = new ArrayList<RenderNode>();
    }

    public void addNode(RenderNode node) {
        this.nodes.add(node);
    }

    @Override
    public void update() {

    }
}
