package game.nodes;

import game.components.Position;

public class RenderNode extends Node {
    Position position;
    // Display object

    public RenderNode(Position position) {
        this.position = position;
    }
}
