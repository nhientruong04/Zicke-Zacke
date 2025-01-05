package com.core;

import com.components.*;
import com.nodes.*;

import javafx.scene.control.Button;

public class NodeCreator {
    public RenderNode createRenderNode(Position position, HiddenState hidden_state) {
        return new RenderNode(position, hidden_state);
    }

    public OctaTileNode createTileNode(Position position, TileSelectedState selected) {
        return new OctaTileNode(position, selected);
    }

    public ButtonNode createButtonNode(Button button) {
        return new ButtonNode(button);
    }

    public PlayerNode createPlayerNode(Position position, FeatherList feathers, FXObject fx_object) {
        return new PlayerNode(position, feathers, fx_object);
    }

    public TrackTileNode createTrackTileNode(Position position, FXObject fx_object) {
        return new TrackTileNode(position, fx_object);
    }
}
