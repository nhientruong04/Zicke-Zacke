package com.core;

import com.components.FXObject;
import com.components.FeatherList;
import com.components.Position;
import com.core.blueprints.BlueprintRegistry;
import com.entities.*;

import javafx.scene.image.ImageView;

import java.util.Map;
import java.util.HashMap;

public class EntityCreator {

    public TrackTile createTrackTile(int track_id, ImageView fx_object) {
        Map<String, Object> params = new HashMap<String, Object>();
        Position position = new Position(track_id);
        FXObject object = new FXObject(fx_object);

        params.put("position", position);
        params.put("fx_object", object);

        return BlueprintRegistry.create("TrackTile", params);
    }

    public OctaTile createOctaTile(int track_id, ImageView fx_object) {
        Map<String, Object> params = new HashMap<String, Object>();
        Position position = new Position(track_id);
        FXObject object = new FXObject(fx_object);

        params.put("position", position);
        params.put("fx_object", object);

        return BlueprintRegistry.create("OctaTile", params);
    }

    public Player createPlayer(int track_id, ImageView fx_object) {
        Map<String, Object> params = new HashMap<String, Object>();
        Feather feather = new Feather();

        Position position = new Position(track_id);
        FeatherList feathers = new FeatherList(feather);
        FXObject object = new FXObject(fx_object);

        params.put("position", position);
        params.put("feather_list", feathers);
        params.put("fx_object", object);

        return BlueprintRegistry.create("Player", params);
    }
}
