package com.entities;

import com.components.HiddenState;
import com.components.Position;
import com.components.TileSelectedState;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class OctaTile extends Tile {
    public TileSelectedState selected;
    public Button button;

    public OctaTile(Position position) {
        this.position = position;
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