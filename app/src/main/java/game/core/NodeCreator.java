package game.core;

import game.components.Position;
import game.nodes.RenderNode;

public class NodeCreator {
    public RenderNode createRenderNode(Position position) {
        return new RenderNode(position);
    }
}
