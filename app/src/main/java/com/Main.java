package com;

import java.util.Map;

import com.components.*;
import com.core.EntityCreator;
import com.core.blueprints.BlueprintRegistry;
import com.entities.Feather;
import com.entities.Tile;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        EntityCreator creator = new EntityCreator();

        Map<String, Object> params = new HashMap<String, Object>();

        Position position = new Position(0, 0, 0);
        FeatherList feather_list = new FeatherList(new Feather());

        params.put("position", (Component) position);
        params.put("feather_list", feather_list);

        Tile track_tile = creator.createTrackTile(0, 84, 32);
        Tile octa_tile = creator.createOctaTile(0, 0, 0);

        System.out.println("Check");   
    }
}
