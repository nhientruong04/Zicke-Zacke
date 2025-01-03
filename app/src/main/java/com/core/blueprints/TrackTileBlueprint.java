package com.core.blueprints;

import com.components.FXObject;
import com.components.Position;
import com.entities.TrackTile;

import java.util.Map;

public class TrackTileBlueprint implements Blueprint<TrackTile> {

    @Override
    public TrackTile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        FXObject fx_object = (FXObject) params.get("fx_object");

        return new TrackTile(position, fx_object);
    }
}