package com.entities;

import com.components.FXObject;
import com.components.HiddenState;
import com.components.Position;
import com.components.TileSelectedState;

import javafx.scene.control.Button;

public class OctaTile extends Tile {
    public TileSelectedState selected;
    public Button button;
    public FXObject fx_object;

    public OctaTile(Position position, FXObject fx_object) {
        this.position = position;
        this.fx_object = fx_object;
        this.hidden_state = new HiddenState();
        this.selected = new TileSelectedState();
    }

    public void setButton(Button button) {
        button.setOnAction(e -> {
            System.out.println("Button pressed");
            this.selected.is_selected = true;
        });

        this.button = button;
    }
}