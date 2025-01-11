package com.systems;

import java.util.ArrayList;

import com.nodes.ButtonNode;
import com.nodes.PlayerNode;
import com.nodes.TrackTileNode;
import com.nodes.OctaTileNode;

import com.core.Settings;
import com.entities.Feather;

public class LogicSystem extends ISystem {
    private ArrayList<OctaTileNode> octaTile_nodes;
    private ArrayList<ButtonNode> button_nodes;
    private ArrayList<PlayerNode> player_nodes;
    private ArrayList<TrackTileNode> trackTile_nodes;

    public int turn_index;

    public LogicSystem(ArrayList<OctaTileNode> octaTile_nodes, ArrayList<ButtonNode> button_nodes, ArrayList<PlayerNode> player_nodes, ArrayList<TrackTileNode> trackTile_nodes) {
        this.octaTile_nodes = octaTile_nodes;
        this.button_nodes = button_nodes;
        this.player_nodes = player_nodes;
        this.trackTile_nodes = trackTile_nodes;

        this.turn_index = 0;
        // this.player_nodes.get(0).setTurn(); // set turn to the first player
    }

    private void disableButtons() {
        this.button_nodes.forEach(button_node -> button_node.button.setDisable(true));
    }

    private void enableButtons() {
        this.button_nodes.forEach(button_node -> button_node.button.setDisable(false));
    }

    private void takeFeathers(PlayerNode p2) {
        // give feather from p2 to p1
        PlayerNode p1 = this.player_nodes.get(this.turn_index); // current player
        
        Feather feather;
        while(!p2.feather_list.feathers.isEmpty()) {
            feather = p2.feather_list.feathers.remove(0);
            p1.feather_list.feathers.add(feather);        
        }
    }

    public int getNextTileId() {
        int playerTileId = this.player_nodes.get(this.turn_index).position.tile_id;
        int nextTileId = (playerTileId + 1) % 24; // 24 track tiles
        
        ArrayList<PlayerNode> player_nodes_cloned = new ArrayList<PlayerNode>(this.player_nodes);
        player_nodes_cloned.remove(this.turn_index); // remove current player
        player_nodes_cloned.sort((o1, o2) -> ((Integer) o1.position.tile_id).compareTo(o2.position.tile_id));
        
        int player_ind = 0;

        while (player_ind<Settings.PLAYERS-1) {
            if (player_nodes_cloned.get(player_ind).position.tile_id==nextTileId) {
                nextTileId++;
            }

            if (nextTileId%24==0) {
                nextTileId = 0;
                player_ind = -1;
            }

            player_ind++;
        }

        return nextTileId;
    }

    private void printCurrentPlayerState(int chosenImgId, int oldTileId, int nextTileId, int nextImgId) {
        System.out.println("Player: " + this.player_nodes.get(this.turn_index).fx_object.img_id);
        System.out.println("Feathers: " + this.player_nodes.get(this.turn_index).feather_list.feathers.size());
        System.out.println("Current tile: " + oldTileId);
        System.out.println("Next tile: " + nextTileId);
        System.out.println("Next ImgId: " + nextImgId);
        System.out.println("Chosen ImgId: " + chosenImgId);
    }

    private void printAllPlayerState() {
        System.out.println("-------------------------");
        for (int i=0; i<this.player_nodes.size(); i++) {
            System.out.println("Player: " + i);
            System.out.println("Feathers: " + this.player_nodes.get(i).feather_list.feathers.size() + ", position " + this.player_nodes.get(i).position.tile_id);
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    private void changeTurn() {
        // remove current player turn
        this.player_nodes.get(this.turn_index).removeTurn();

        // get new turn index and set turn
        this.turn_index = (this.turn_index + 1) % Settings.PLAYERS;
        this.player_nodes.get(this.turn_index).setTurn();
    }

    @Override
    public void update() {
        // for (TileNode node: this.tile_nodes) {
        //     if (node.selected.is_selected) {
        //         this.disableButtons();

        //         node.selected.is_selected = false;

        //         int chosenTileId = node.position.tile_id;
        //         int oldTileId = this.player_nodes.get(this.turn_index).position.tile_id;
        //         int nextTileId = this.getNextTileId();
        //         System.out.println("Next tile: " + nextTileId);
        //         System.out.println("Chosen tile: " + chosenTileId);

        //         // if player chose correctly
        //         if (chosenTileId==nextTileId) {
        //             // loop to get feathers
        //             int player_ind = 0;
        //             while (player_ind<Settings.PLAYERS) {
        //                 // only consider players not in turn
        //                 if (player_ind != this.turn_index) {
        //                     // the tile id of the current player
        //                     int currentPlayerTileId = this.player_nodes.get(player_ind).position.tile_id;

        //                     // current player in the way of the player in turn
        //                     if (currentPlayerTileId>oldTileId & currentPlayerTileId<nextTileId) {
        //                         this.takeFeathers(this.player_nodes.get(player_ind));
        //                     }
        //                 }

        //                 player_ind++;
        //             }

        //             // change position for MoveSystem to process
        //             this.player_nodes.get(this.turn_index).position.tile_id = nextTileId;
        //         }

        //         // change turn after finishing the logic
        //         this.changeTurn();

        //         this.enableButtons();
        //         break;
        //     }
        // }
    }   
}
