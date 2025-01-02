package com.core.blueprints;

import com.components.Position;
import com.entities.OctaTile;

import java.util.Map;

public class OctaTileBlueprint implements Blueprint<OctaTile> {

    @Override
    public OctaTile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");

        return new OctaTile(position);
    }
}