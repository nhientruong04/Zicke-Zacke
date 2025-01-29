package com.entities;

import com.components.FXObject;
import com.components.FeatherList;
import com.components.Position;

public class Player extends Entity {
    public Position position;    
    public FeatherList feather_list;
    public FXObject fx_object;

    public Player(Position position, FeatherList feather_list, FXObject fx_object) {
        this.position = position;
        this.fx_object = fx_object;
        this.feather_list = feather_list;
    }
}