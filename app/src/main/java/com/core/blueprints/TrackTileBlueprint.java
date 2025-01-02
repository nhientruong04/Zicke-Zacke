package com.core.blueprints;

import com.components.Position;
import com.entities.TrackTile;

import java.util.Map;

public class TrackTileBlueprint implements Blueprint<TrackTile> {

    @Override
    public TrackTile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");

        return new TrackTile(position);
    }
}