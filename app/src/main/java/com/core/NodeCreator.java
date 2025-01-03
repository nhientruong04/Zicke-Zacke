package com.core;

import com.components.FeatherList;
import com.components.HiddenState;
import com.components.Position;
import com.components.TileSelectedState;
import com.nodes.ButtonNode;
import com.nodes.PlayerNode;
import com.nodes.RenderNode;
import com.nodes.TileNode;

import javafx.scene.control.Button;

public class NodeCreator {
    public RenderNode createRenderNode(Position position, HiddenState hidden_state) {
        return new RenderNode(position, hidden_state);
    }

    public TileNode createTileNode(Position position, TileSelectedState selected) {
        return new TileNode(position, selected);
    }

    public ButtonNode createButtonNode(Button button) {
        return new ButtonNode(button);
    }

    public PlayerNode createPlayerNode(Position position, FeatherList feathers) {
        return new PlayerNode(position, feathers);
    }
}
