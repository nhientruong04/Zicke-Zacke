package com.systems;

import com.components.HiddenState;
import com.nodes.RenderNode;

import java.util.ArrayList;

public class RenderSystem extends ISystem {

    private ArrayList<RenderNode> nodes;

    public RenderSystem() {
        this.nodes = new ArrayList<RenderNode>();
    }

    public void addNode(RenderNode node) {
        this.nodes.add(node);
    }

    @Override
    public void update() {
        for (int i=0; i<this.nodes.size(); i++) {

            if (this.nodes.get(i).hidden_state != null) {
                System.out.print("* ");
            } else {
                System.out.print("" + this.nodes.get(i).position.tile_id + " ");
            }
            
        }
        System.out.println();
    }
}
