package com.entities;

import com.components.FeatherList;
import com.components.Position;

public class Player {
    public Position position;    
    public FeatherList feather_list;

    public Player(Position position, FeatherList feather_list) {
        this.position = position;
        this.feather_list = feather_list;
    }
}