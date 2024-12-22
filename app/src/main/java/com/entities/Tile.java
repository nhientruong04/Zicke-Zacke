package com.entities;

import com.components.Position;
import com.components.HiddenState;

public class Tile {
    public Position position;
    public HiddenState hidden_state;
    // public img;

    public Tile(Position position, HiddenState hidden_state) {
        this.position = position;
        this.hidden_state = hidden_state;
    }
}