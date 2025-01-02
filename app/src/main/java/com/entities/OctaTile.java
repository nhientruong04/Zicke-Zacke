package com.entities;

import com.components.HiddenState;
import com.components.Position;
import com.components.TileSelectedState;

import javafx.scene.control.Button;

public class OctaTile extends Tile {
    public TileSelectedState selected;
    public Button button;

    public OctaTile(Position position) {
        this.position = position;
        this.hidden_state = new HiddenState();
        this.selected = null;
    }

    public void setButton(Button button) {
        button.setOnAction(e -> {
            this.selected = new TileSelectedState();
        });

        this.button = button;
    }
}