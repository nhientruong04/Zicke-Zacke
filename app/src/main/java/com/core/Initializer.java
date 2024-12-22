package com.core;

import java.util.HashMap;
import java.util.Map;

import com.entities.Tile;
import com.nodes.RenderNode;
import com.systems.RenderSystem;

public class Initializer {
    Engine engine;
    RenderSystem render_system;

    public Initializer(Engine engine) {
        this.engine = engine;
    }

    private void iniMap() {
        RenderNode render_node;
        int track_id = 1;
        while (track_id<=Settings.MAX_TILES) {
            Tile track_tile = engine.entity_creator.createTrackTile(track_id, 0, 0);
            Tile octa_tile = engine.entity_creator.createOctaTile(track_id, 0, 0);

            render_node = engine.node_creator.createRenderNode(track_tile.position, track_tile.hidden_state);
            render_system.addNode(render_node);
            render_node = engine.node_creator.createRenderNode(octa_tile.position, octa_tile.hidden_state);
            render_system.addNode(render_node);

            track_id++;
        }
    }

    public void init() {
        this.render_system = new RenderSystem();

        this.iniMap();

        this.engine.addSystem(this.render_system);
    }
}
