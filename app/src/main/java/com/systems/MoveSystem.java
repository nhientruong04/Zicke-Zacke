package com.systems;

import java.util.ArrayList;

import com.nodes.PlayerNode;
import com.nodes.TrackTileNode;

public class MoveSystem extends ISystem {
    private ArrayList<TrackTileNode> trackTile_nodes;
    private ArrayList<PlayerNode> player_nodes;

    public MoveSystem(ArrayList<TrackTileNode> trackTile_nodes, ArrayList<PlayerNode> player_nodes) {
        this.trackTile_nodes = trackTile_nodes;
        this.player_nodes = player_nodes;
    }

    @Override
    public void update() {
        for (int i=0; i<this.player_nodes.size(); i++) {
            int dest_tileId = this.player_nodes.get(i).position.tile_id; // get the presumed position

            

            
        }
    }
}
