package com.nodes;

import com.components.FXObject;
import com.components.Position;

public class TrackTileNode extends EntityNode {
    public Position position;

    public TrackTileNode (Position position, FXObject fx_object) {
        this.position = position;
        this.fx_object = fx_object;
    }
}
