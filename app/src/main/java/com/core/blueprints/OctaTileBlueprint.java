package com.core.blueprints;

import com.components.FXObject;
import com.components.Position;
import com.entities.OctaTile;

import java.util.Map;

public class OctaTileBlueprint implements Blueprint<OctaTile> {

    @Override
    public OctaTile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        FXObject fx_object = (FXObject) params.get("fx_object");

        return new OctaTile(position, fx_object);
    }
}