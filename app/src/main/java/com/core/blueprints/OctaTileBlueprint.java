package com.core.blueprints;

import com.components.FXObject;
import com.entities.OctaTile;

import java.util.Map;

public class OctaTileBlueprint implements Blueprint<OctaTile> {

    @Override
    public OctaTile create(Map<String, Object> params) {
        FXObject fx_object = (FXObject) params.get("fx_object");

        return new OctaTile(fx_object);
    }
}