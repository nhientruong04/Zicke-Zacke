package com.systems;

import java.util.ArrayList;

import com.core.Settings;
import com.gui.GUI;
import com.nodes.PlayerNode;

public class WinSystem extends ISystem{
    ArrayList<PlayerNode> player_nodes;
    GUI gui;

    public WinSystem(ArrayList<PlayerNode> player_nodes, GUI gui) {
        this.player_nodes = player_nodes;
        this.gui = gui;
    }

    @Override
    public void update() {
        for (PlayerNode node: this.player_nodes) {
            if(node.feather_list.feathers.size()==Settings.getInstance().getPlayerNumber()) {
                this.gui.primaryStage.setScene(gui.createWinScene(node.fx_object.img_id));
            }
        }
    }
}
