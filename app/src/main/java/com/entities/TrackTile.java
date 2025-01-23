package com.entities;

import com.components.FXObject;
import com.components.Position;

public class TrackTile extends Tile {
    public Position position;

    public TrackTile(Position position, FXObject fx_object) {
        this.position = position;
        this.fx_object = fx_object;
    }
}
