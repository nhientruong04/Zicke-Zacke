package com.systems;

import java.util.ArrayList;

import com.nodes.ButtonNode;
import com.nodes.PlayerNode;
import com.nodes.TrackTileNode;

import javafx.scene.image.ImageView;

import com.nodes.OctaTileNode;

import com.core.Settings;
import com.core.Utils;
import javafx.scene.media.AudioClip;

public class LogicSystem extends ISystem {
    private ArrayList<OctaTileNode> octaTile_nodes;
    private ArrayList<ButtonNode> button_nodes;
    private ArrayList<PlayerNode> player_nodes;
    private ArrayList<TrackTileNode> trackTile_nodes;

    private AudioClip correct_sound;
    private AudioClip incorrect_sound;

    private int turn_index;

    public LogicSystem(ArrayList<OctaTileNode> octaTile_nodes, ArrayList<ButtonNode> button_nodes, ArrayList<PlayerNode> player_nodes, ArrayList<TrackTileNode> trackTile_nodes) {
        this.octaTile_nodes = octaTile_nodes;
        this.button_nodes = button_nodes;
        this.player_nodes = player_nodes;
        this.trackTile_nodes = trackTile_nodes;

        this.correct_sound = new AudioClip(getClass().getResource("/sound/feather_take_sound.wav").toString());
        this.incorrect_sound = new AudioClip(getClass().getResource("/sound/wrong_choose_sound.wav").toString());

        this.turn_index = 0;
        this.player_nodes.get(0).setTurn(); // set turn to the first player
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
        
        ImageView feather;
        while(!p2.feather_list.feathers.isEmpty()) {
            feather = p2.feather_list.feathers.remove(0);
            p1.feather_list.feathers.add(feather);        
        }
    }

    private void printCurrentPlayerState(int chosenImgId) {
        int oldTileId = this.player_nodes.get(this.turn_index).position.tile_id;
        int nextTileId = Utils.getPlayerNextRawTileId(this.player_nodes);
        int nextImgId = this.trackTile_nodes.get(nextTileId%24).fx_object.img_id;

        System.out.println("-------------------------");
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
            System.out.println("Player: " + i + ". Feathers: " + this.player_nodes.get(i).feather_list.feathers.size() + ", position " + this.player_nodes.get(i).position.tile_id);
        }
    }

    private void changeTurn() {
        // remove current player turn
        this.player_nodes.get(this.turn_index).removeTurn();

        // get new turn index and set turn
        this.turn_index = (this.turn_index + 1) % Settings.getInstance().getPlayerNumber();
        this.player_nodes.get(this.turn_index).setTurn();
    }

    @Override
    public void update() {
        for (OctaTileNode node: this.octaTile_nodes) {
            if (node.selected.is_selected) {
                this.disableButtons();

                node.selected.is_selected = false;

                int chosenImgId = node.fx_object.img_id;
                int oldTileId = this.player_nodes.get(this.turn_index).position.tile_id;
                int nextTileId = Utils.getPlayerNextRawTileId(this.player_nodes);
                int nextImgId = this.trackTile_nodes.get(nextTileId%24).fx_object.img_id;

                printCurrentPlayerState(chosenImgId);

                // if player chose correctly
                if (chosenImgId==nextImgId) {
                    
                    while (++oldTileId!=nextTileId) {
                        // loop to get feathers
                        int player_ind = 0;

                        while (player_ind<Settings.getInstance().getPlayerNumber()) {
                            // only consider players not in turn
                            if (player_ind != this.turn_index) {
                                // the tile id of the current player
                                int currentPlayerTileId = this.player_nodes.get(player_ind).position.tile_id;
    
                                // current player in the way of the player in turn
                                if (currentPlayerTileId==oldTileId%24) {
                                    this.takeFeathers(this.player_nodes.get(player_ind));
                                }
                            }
    
                            player_ind++;
                        }
                    }

                    this.correct_sound.play();

                    this.printCurrentPlayerState(chosenImgId);
                    // change position for MoveSystem to process
                    this.player_nodes.get(this.turn_index).position.tile_id = nextTileId%24; // since nextTileId is not modulo

                    this.printAllPlayerState();
                } else {
                    this.incorrect_sound.play();

                    // change turn if player chose wrong
                    this.changeTurn();
                }

                this.enableButtons();
                break;
            }
        }
    }   
}
