package com.nodes;

import com.components.HiddenState;
import com.components.Position;

public class RenderNode extends Node {
    public Position position;
    public HiddenState hidden_state;
    // Display object

    public RenderNode(Position position, HiddenState hidden_state) {
        this.position = position;
        this.hidden_state = hidden_state;
    }
}
