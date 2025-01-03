package com.entities;

import com.components.FeatherList;
import com.components.Position;

import javafx.scene.image.ImageView;

public class Player {
    public Position position;    
    public FeatherList feather_list;
    public ImageView object;

    public Player(Position position, FeatherList feather_list, ImageView fx_object) {
        this.position = position;
        this.object = fx_object;
        this.feather_list = feather_list;
    }
}