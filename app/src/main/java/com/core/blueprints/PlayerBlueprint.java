package com.core.blueprints;

import com.components.FeatherList;
import com.components.Position;
import com.entities.Player;

import javafx.scene.image.ImageView;

import java.util.Map;

public class PlayerBlueprint implements Blueprint<Player>  {

    @Override
    public Player create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        FeatherList feather_list = (FeatherList) params.get("feather_list");
        ImageView object = (ImageView) params.get("fx_object");

        return new Player(position, feather_list, object);
    }
}