package com.entities;

import com.components.FXObject;
import com.components.Position;

public class TrackTile extends Tile {
    public FXObject fx_object;

    public TrackTile(Position position, FXObject fx_object) {
        this.position = position;
        this.fx_object = fx_object;
        this.hidden_state = null;
    }
}
