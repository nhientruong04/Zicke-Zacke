package com.systems;

import com.components.HiddenState;
import com.core.Utils;
import com.nodes.PlayerNode;
import com.nodes.RenderNode;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class TurnHUDDisplaySystem extends ISystem {

    private ArrayList<PlayerNode> player_nodes;
    private ArrayList<ImageView> player_images;
    private ArrayList<ImageView> tile_images;

    private ImageView old_tile;
    private ImageView old_player;
    private GridPane hud;
    private int turn_index;

    public TurnHUDDisplaySystem(ArrayList<PlayerNode> player_nodes, GridPane hud, List<Integer> img_indices_list) {
        this.player_nodes = player_nodes;
        this.hud = hud;
        this.player_images = new ArrayList<ImageView>();
        this.tile_images = new ArrayList<ImageView>();

        this.getAllTileImageViews(img_indices_list);
        this.getAllPlayerImageViews();

        for(int i=0; i<this.player_nodes.size(); i++) {
            if (this.player_nodes.get(i).inTurn) {
                this.turn_index = i;
                break;
            }
        }

        this.old_tile = null;
        this.old_player = null;
    }

    private void start() {
        ImageView player = this.player_images.get(this.turn_index);
        ImageView tile = this.tile_images.get(Utils.getPlayerNextRawTileId(this.player_nodes)%24);

        this.hud.add(player, 0, 1);
        this.hud.add(tile, 1, 1);

        this.old_player = player;
        this.old_tile = tile;
    }

    private void getAllTileImageViews(List<Integer> img_indices_list) {
        for(int i=0; i<img_indices_list.size(); i++) {
            Image tile_img = new Image(getClass().getResource("/track_tiles/" + img_indices_list.get(i) + ".png").toExternalForm());
            ImageView tile_imgView = new ImageView(tile_img);
            tile_imgView.fitWidthProperty().bind(this.hud.maxWidthProperty().multiply(0.3));
            tile_imgView.fitHeightProperty().bind(this.hud.maxHeightProperty().multiply(0.55));
            tile_imgView.setPreserveRatio(true);

            this.tile_images.add(tile_imgView);
        }
    }

    private void getAllPlayerImageViews() {
        for(int i=0; i<this.player_nodes.size(); i++) {
            Image player_img = new Image(getClass().getResource("/chicken/chicken_" + this.player_nodes.get(i).fx_object.img_id + "/1.png").toExternalForm());
            ImageView player_imgView = new ImageView(player_img);
            player_imgView.fitWidthProperty().bind(this.hud.maxWidthProperty().multiply(0.3));
            player_imgView.fitHeightProperty().bind(this.hud.maxHeightProperty().multiply(0.55));
            player_imgView.setPreserveRatio(true);

            this.player_images.add(player_imgView);
        }
    }

    @Override
    public void update() {
        if (this.old_player==null && this.old_tile==null) {
            this.start();
        }

        for (int i=0; i<this.player_nodes.size(); i++) {
            
            if (this.player_nodes.get(i).inTurn) {
                if (i != this.turn_index) {
                    ImageView new_player = this.player_images.get(i);

                    this.hud.getChildren().remove(this.old_player);
                    this.hud.add(new_player, 0, 1);
                    this.old_player = new_player;

                    this.turn_index = i;
                }
            }

            if (this.old_tile!=this.tile_images.get(Utils.getPlayerNextRawTileId(this.player_nodes)%24)) {
                
                ImageView new_tile = this.tile_images.get(Utils.getPlayerNextRawTileId(this.player_nodes)%24);

                this.hud.getChildren().remove(this.old_tile);
                this.hud.add(new_tile, 1, 1);

                this.old_tile = new_tile;

                break;
            }
        }
    }
}
