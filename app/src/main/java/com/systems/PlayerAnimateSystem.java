package com.systems;

import java.util.ArrayList;
import java.util.List;

import com.core.Utils;
import com.nodes.AnimateNode;

import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class PlayerAnimateSystem extends ISystem{
    private ArrayList<AnimateNode> animate_nodes;
    private ArrayList<Timeline> animation_timeLines;

    public PlayerAnimateSystem(ArrayList<AnimateNode> animate_nodes) {
        this.animate_nodes = animate_nodes;
        this.animation_timeLines = new ArrayList<Timeline>();

        this.start();
    }

    private void start() {
        for (int i=0; i<this.animate_nodes.size(); i++) {
            int img_id = this.animate_nodes.get(i).player_object.img_id;
            List<Image> frames = new ArrayList<Image>();

            frames.add(new Image(getClass().getResource("/chicken/chicken_" + img_id + "/1.png").toExternalForm()));
            frames.add(new Image(getClass().getResource("/chicken/chicken_" + img_id + "/2.png").toExternalForm()));
            frames.add(new Image(getClass().getResource("/chicken/chicken_" + img_id + "/3.png").toExternalForm()));
            frames.add(new Image(getClass().getResource("/chicken/chicken_" + img_id + "/4.png").toExternalForm()));

            Timeline sprite_timeLine = Utils.createAnimation(this.animate_nodes.get(i).player_object.object, frames);

            this.animation_timeLines.add(sprite_timeLine);
        }

        for (int i=0; i<this.animation_timeLines.size(); i++) {
            this.animation_timeLines.get(i).play();
        }
    }
    
    @Override
    public void update() {

    }
}
