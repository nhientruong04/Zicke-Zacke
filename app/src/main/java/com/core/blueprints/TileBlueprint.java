package com.core.blueprints;

import com.components.Position;
import com.components.TileChoosable;
import com.entities.Tile;

import java.util.Map;

public class TileBlueprint implements Blueprint<Tile> {

    @Override
    public Tile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        TileChoosable choosable = (TileChoosable) params.getOrDefault("choosable", null);

        return new Tile(position, choosable);
    }
}