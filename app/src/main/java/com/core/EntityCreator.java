package com.core;

import com.components.Position;
import com.components.HiddenState;
import com.core.blueprints.BlueprintRegistry;
import com.entities.Tile;

import java.util.Map;
import java.util.HashMap;

public class EntityCreator {

    public Tile createTrackTile(int track_id, int x, int y) {
        Map<String, Object> params = new HashMap<String, Object>();
        Position position = new Position(track_id, x, y);

        params.put("position", position);

        return BlueprintRegistry.create("Tile", params);
    }

    public Tile createOctaTile(int track_id, int x, int y) {
        Map<String, Object> params = new HashMap<String, Object>();
        Position position = new Position(track_id, x, y);
        HiddenState hidden = new HiddenState();

        params.put("position", position);
        params.put("hidden_state", hidden);

        return BlueprintRegistry.create("Tile", params);
    }
}
