package com.core.blueprints;

import java.util.HashMap;
import java.util.Map;

public class BlueprintRegistry {

    private static final Map<String, Blueprint<?>> blueprints = new HashMap<>();

    // Static initializer for registering blueprints
    static {
        registerBlueprint("TrackTile", new TrackTileBlueprint());
        registerBlueprint("OctaTile", new OctaTileBlueprint());
        registerBlueprint("Player", new PlayerBlueprint());
    }

    // Method to register a blueprint
    public static <T> void registerBlueprint(String name, Blueprint<T> blueprint) {
        blueprints.put(name, blueprint);
    }

    // Method to retrieve and use a blueprint
    @SuppressWarnings("unchecked")
    public static <T> T create(String name, Map<String, Object> params) {
        Blueprint<T> blueprint = (Blueprint<T>) blueprints.get(name);
        if (blueprint == null) {
            throw new IllegalArgumentException("No blueprint found for name: " + name);
        }
        return blueprint.create(params);
    }
}