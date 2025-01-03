package com.nodes;

import com.components.FXObject;
import com.components.Position;

public class TrackTileNode extends Node {
    public Position position;
    public FXObject fx_object;

    public TrackTileNode (Position position, FXObject fx_object) {
        this.position = position;
        this.fx_object = fx_object;
    }
}
