package game.systems;

import java.util.ArrayList;

import game.nodes.Node;

public class RenderSystem extends System {

    protected ArrayList<Node> nodes;

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
