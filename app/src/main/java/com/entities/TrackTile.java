package com.entities;

import com.components.Position;

public class TrackTile extends Tile {
    public TrackTile(Position position) {
        this.position = position;
        this.hidden_state = null;
    }
}
