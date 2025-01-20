package com.systems;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;
import java.util.ArrayList;

import com.core.Settings;
import com.nodes.HUDNode;

public class PlayerHUDSystem extends ISystem {
    private ArrayList<HUDNode> hud_nodes;
    private Image feather_img;

    public PlayerHUDSystem(ArrayList<HUDNode> hud_nodes) {
        this.hud_nodes = hud_nodes;

        Image feather_img = new Image(getClass().getResource("/feather/feather.png").toExternalForm());
        
        
        this.feather_img = feather_img;
    }

    private ImageView getHUDFeather(FlowPane hud_row) {
        ImageView feather = new ImageView(this.feather_img);
        feather.fitWidthProperty().bind(hud_row.maxWidthProperty().divide(5));
        feather.fitHeightProperty().bind(hud_row.maxHeightProperty());

        return feather;
    }

    @Override
    public void update() {
        for (HUDNode node: this.hud_nodes) {
            if (node.hud.feather_num > node.feather_list.feathers.size()+1) {
                while (node.hud.feather_num!=node.feather_list.feathers.size()) {
                    node.hud.feathers_placeholder.getChildren().remove(1);

                    node.hud.feather_num--;
                }
            }

            if (node.hud.feather_num < node.feather_list.feathers.size()) {
                while (node.hud.feather_num!=node.feather_list.feathers.size()) {
                    ImageView feather = this.getHUDFeather(node.hud.feathers_placeholder);
                    node.hud.feathers_placeholder.getChildren().add(feather);

                    node.hud.feather_num++;
                }
            }
        }
    }
}
