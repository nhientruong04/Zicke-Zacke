package com.systems;

import java.util.ArrayList;

import com.nodes.ButtonNode;
import com.nodes.PlayerNode;
import com.nodes.TileNode;

import javafx.scene.control.Button;

import com.core.Settings;

public class LogicSystem extends ISystem {
    private ArrayList<TileNode> tile_nodes;
    private ArrayList<ButtonNode> button_nodes;
    private ArrayList<PlayerNode> player_nodes;

    public int turn_index;

    public LogicSystem(ArrayList<TileNode> tile_nodes, ArrayList<ButtonNode> button_nodes, ArrayList<PlayerNode> player_nodes) {
        this.tile_nodes = tile_nodes;
        this.button_nodes = button_nodes;
        this.player_nodes = player_nodes;

        this.turn_index = 0;
    }

    private void disableButtons() {
        this.button_nodes.forEach(button_node -> button_node.button.setDisable(true));
    }

    private void enableButtons() {
        this.button_nodes.forEach(button_node -> button_node.button.setDisable(false));
    }

    public int getNextTileId() {
        int playerTileId = this.player_nodes.get(this.turn_index).position.tile_id;
        int nextTileId = (playerTileId + 1) % Settings.MAX_TILES;
        
        ArrayList<PlayerNode> player_nodes_cloned = new ArrayList<PlayerNode>(this.player_nodes);
        player_nodes_cloned.sort((o1, o2) -> ((Integer) o1.position.tile_id).compareTo(o2.position.tile_id));
        
        for (int i=0; i<player_nodes_cloned.size(); i++) {
            if (player_nodes_cloned.get(i).position.tile_id==nextTileId) {
                nextTileId++;
            }
        }

        return nextTileId;
    }

    @Override
    public void update() {
        for (TileNode node: this.tile_nodes) {
            if (node.selected != null) {
                this.disableButtons();

                int chosenTileId = node.position.tile_id;

                // if (chosenTileId)

                this.enableButtons();
            }
        }
    }   
}
