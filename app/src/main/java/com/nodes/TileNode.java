package com.nodes;

import com.components.Position;
import com.components.TileSelectedState;

public class TileNode extends Node {
    public Position position;
    public TileSelectedState selected;

    public TileNode (Position position, TileSelectedState selected) {
        this.position = position;
        this.selected = selected; // init don't need to declare this
    }
}
