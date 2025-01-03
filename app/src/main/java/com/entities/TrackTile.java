package com.entities;

import com.components.Position;

import javafx.scene.image.ImageView;

public class TrackTile extends Tile {
    public ImageView object;

    public TrackTile(Position position, ImageView fx_object) {
        this.position = position;
        this.object = fx_object;
        this.hidden_state = null;
    }
}
