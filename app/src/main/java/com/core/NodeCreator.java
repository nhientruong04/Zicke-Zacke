package com.core;

import com.components.*;
import com.nodes.*;

import javafx.scene.control.Button;

public class NodeCreator {
    public OctaTileNode createOctaTileNode(FXObject fx_object, TileSelectedState selected) {
        return new OctaTileNode(fx_object, selected);
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
