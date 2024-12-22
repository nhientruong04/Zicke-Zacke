package com.systems;

import com.nodes.Node;
import com.nodes.RenderNode;

import java.util.ArrayList;

public class RenderSystem extends ISystem {

    protected ArrayList<RenderNode> nodes;

    public RenderSystem() {
        this.nodes = new ArrayList<RenderNode>();
    }

    public void addNode(RenderNode node) {
        this.nodes.add(node);
    }

    @Override
    public void update() {
        for (int i=0; i<this.nodes.size(); i++) {
            System.out.print("" + this.nodes.get(i).position.tile_id + " ");
        }
        System.out.println();
    }
}
