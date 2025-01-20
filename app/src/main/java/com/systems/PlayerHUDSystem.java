package com.systems;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.ArrayList;

import com.core.Settings;
import com.nodes.HUDNode;

public class PlayerHUDSystem extends ISystem {
    private ArrayList<HUDNode> hud_nodes;
    private ImageView feather_img;

    public PlayerHUDSystem(ArrayList<HUDNode> hud_nodes) {
        this.hud_nodes = hud_nodes;

        Image feather = new Image(getClass().getResource("/feather/feather.png").toExternalForm());
        ImageView feather_img = new ImageView(feather);
        feather_img.setFitHeight(Settings.FEATHER_HEIGHT);
        feather_img.setFitWidth(Settings.FEATHER_WIDTH);
        
        this.feather_img = feather_img;
    }

    @Override
    public void update() {
        for (HUDNode node: this.hud_nodes) {
            if (node.hud.feather_num > node.feather_list.feathers.size()) {
                while (node.hud.feather_num!=node.feather_list.feathers.size()) {
                    node.hud.feathers_placeholder.getChildren().remove(0);

                    node.hud.feather_num--;
                }
            }

            if (node.hud.feather_num < node.feather_list.feathers.size()) {
                while (node.hud.feather_num!=node.feather_list.feathers.size()) {
                    node.hud.feathers_placeholder.getChildren().add(this.feather_img);

                    node.hud.feather_num++;
                }
            }
        }
    }
}
