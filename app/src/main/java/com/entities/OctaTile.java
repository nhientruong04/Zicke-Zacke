package com.entities;

import com.components.FXObject;
import com.components.HiddenState;
import com.components.TileSelectedState;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class OctaTile extends Tile {
    public TileSelectedState selected;
    public Button button;

    public OctaTile(FXObject fx_object) {
        this.fx_object = fx_object;
        this.hidden_state = new HiddenState();
        this.selected = new TileSelectedState();
    }

    public void setButton(Button button) {
        button.setOnAction(e -> {
            System.out.println("Button pressed");
            this.selected.is_selected = true;

            button.setOpacity(0);

            // Create a PauseTransition for 2 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(ev -> button.setOpacity(1)); // Restore opacity
            pause.play(); // Start the timer
        });

        this.button = button;
    }
}