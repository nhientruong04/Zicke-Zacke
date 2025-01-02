package com.nodes;

import com.components.FeatherList;
import com.components.Position;

public class PlayerNode extends Node {
    public Position position;
    public FeatherList feather_list;
    public boolean inTurn;

    public PlayerNode (Position position, FeatherList feather_list) {
        this.position = position;
        this.feather_list = feather_list;
        this.inTurn = false;
    }

    public void setTurn() {
        this.inTurn = true;
    }

    public void removeTurn() {
        this.inTurn = false;
    }
}
