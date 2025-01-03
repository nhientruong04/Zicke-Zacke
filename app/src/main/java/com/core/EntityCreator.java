package com.core;

import com.components.FeatherList;
import com.components.Position;
import com.core.blueprints.BlueprintRegistry;
import com.entities.Feather;
import com.entities.OctaTile;
import com.entities.Player;
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

    public Player createPlayer(int track_id, int x, int y) {
        Map<String, Object> params = new HashMap<String, Object>();
        Feather feather = new Feather();

        Position position = new Position(track_id, x, y);
        FeatherList feathers = new FeatherList(feather);

        params.put("position", position);
        params.put("feather_list", feathers);

        return BlueprintRegistry.create("Player", params);
    }
}
