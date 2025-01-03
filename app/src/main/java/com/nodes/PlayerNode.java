package com.nodes;

import com.components.FXObject;
import com.components.FeatherList;
import com.components.Position;

public class PlayerNode extends Node {
    public Position position;
    public FeatherList feather_list;
    public FXObject fx_object;
    public boolean inTurn;

    public PlayerNode (Position position, FeatherList feather_list, FXObject fx_object) {
        this.position = position;
        this.feather_list = feather_list;
        this.fx_object = fx_object;
        this.inTurn = false;
    }

    public void setTurn() {
        this.inTurn = true;
    }

    public void removeTurn() {
        this.inTurn = false;
    }
}
