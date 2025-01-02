package com.core;

import com.components.Position;
import com.core.blueprints.BlueprintRegistry;
import com.entities.OctaTile;
import com.entities.Tile;
import com.entities.TrackTile;

import java.util.Map;
import java.util.HashMap;

public class EntityCreator {

    public TrackTile createTrackTile(int track_id, int x, int y) {
        Map<String, Object> params = new HashMap<String, Object>();
        Position position = new Position(track_id, x, y);

        params.put("position", position);

        return BlueprintRegistry.create("TrackTile", params);
    }

    public OctaTile createOctaTile(int track_id, int x, int y) {
        Map<String, Object> params = new HashMap<String, Object>();
        Position position = new Position(track_id, x, y);

        params.put("position", position);

        return BlueprintRegistry.create("OctaTile", params);
    }
}
