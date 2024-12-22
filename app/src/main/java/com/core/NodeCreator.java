package com.core;

import com.components.HiddenState;
import com.components.Position;
import com.nodes.RenderNode;

public class NodeCreator {
    public RenderNode createRenderNode(Position position, HiddenState hidden_state) {
        return new RenderNode(position, hidden_state);
    }
}
