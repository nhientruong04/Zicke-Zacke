package com.core;

import com.components.Position;
import com.nodes.RenderNode;

public class NodeCreator {
    public RenderNode createRenderNode(Position position) {
        return new RenderNode(position);
    }
}
