package com.core.blueprints;

import com.components.Position;
import com.components.HiddenState;
import com.entities.Tile;

import java.util.Map;

public class TileBlueprint implements Blueprint<Tile> {

    @Override
    public Tile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        HiddenState hidden_state = (HiddenState) params.getOrDefault("hidden_state", null);

        return new Tile(position, hidden_state);
    }
}