package com.core.blueprints;

import com.components.Position;
import com.entities.TrackTile;

import javafx.scene.image.ImageView;

import java.util.Map;

public class TrackTileBlueprint implements Blueprint<TrackTile> {

    @Override
    public TrackTile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        ImageView object = (ImageView) params.get("fx_object");

        return new TrackTile(position, object);
    }
}