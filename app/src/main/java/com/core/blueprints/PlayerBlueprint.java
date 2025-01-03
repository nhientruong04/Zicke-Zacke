package com.core.blueprints;

import com.components.FXObject;
import com.components.FeatherList;
import com.components.Position;
import com.entities.Player;

import java.util.Map;

public class PlayerBlueprint implements Blueprint<Player>  {

    @Override
    public Player create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        FeatherList feather_list = (FeatherList) params.get("feather_list");
        FXObject fx_object = (FXObject) params.get("fx_object");

        return new Player(position, feather_list, fx_object);
    }
}