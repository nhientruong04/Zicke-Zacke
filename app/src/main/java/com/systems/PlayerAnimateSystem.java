package com.systems;

import java.util.ArrayList;
import java.util.List;

import com.core.Utils;
import com.nodes.PlayerNode;

import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class PlayerAnimateSystem extends ISystem{
    private ArrayList<PlayerNode> player_nodes;
    private ArrayList<Timeline> animation_timeLines;
    private ArrayList<Image> default_frames;
    private int turn_index;

    public PlayerAnimateSystem(ArrayList<PlayerNode> player_nodes) {
        this.player_nodes = player_nodes;
        this.animation_timeLines = new ArrayList<Timeline>();
        this.default_frames = new ArrayList<Image>();

        for(int i=0; i<this.player_nodes.size(); i++) {
            if (this.player_nodes.get(i).inTurn) {
                this.turn_index = i;
                break;
            }
        }

        this.start();
    }

    private void start() {
        for (int i=0; i<this.player_nodes.size(); i++) {
            int img_id = this.player_nodes.get(i).fx_object.img_id;
            List<Image> frames = new ArrayList<Image>();

            Image default_frame = new Image(getClass().getResource("/chicken/chicken_" + img_id + "/1.png").toExternalForm());

            frames.add(default_frame);
            frames.add(new Image(getClass().getResource("/chicken/chicken_" + img_id + "/2.png").toExternalForm()));
            frames.add(new Image(getClass().getResource("/chicken/chicken_" + img_id + "/3.png").toExternalForm()));
            frames.add(new Image(getClass().getResource("/chicken/chicken_" + img_id + "/4.png").toExternalForm()));

            Timeline sprite_timeLine = Utils.createAnimation(this.player_nodes.get(i).fx_object.object, frames);

            this.animation_timeLines.add(sprite_timeLine);
            this.default_frames.add(default_frame);
        }

        this.animation_timeLines.get(this.turn_index).play();
    }

    private void stopAnimation(int index) {
        this.animation_timeLines.get(index).stop();
        this.player_nodes.get(index).fx_object.object.setImage(this.default_frames.get(index));
    }

    private void playAnimation(int index) {
        this.animation_timeLines.get(index).play();
    }
    
    @Override
    public void update() {
        for (int i=0; i<this.player_nodes.size(); i++) {
            if (this.player_nodes.get(i).inTurn && i != this.turn_index) {
                this.stopAnimation(this.turn_index);
                this.playAnimation(i);
                             
                this.turn_index = i;  
            }
        }
    }
}
