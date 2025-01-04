package com.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import com.components.FXObject;
import com.entities.*;
import com.nodes.*;
import com.systems.*;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Initializer {
    private Engine engine;
    private RenderSystem render_system;
    private LogicSystem logic_system;
    private MoveSystem move_system;

    public Initializer(Engine engine) {
        this.engine = engine;
    }

    private void createMap(GridPane gridPane, ArrayList<TileNode> tile_nodes_list, ArrayList<ButtonNode> button_nodes_list, ArrayList<TrackTileNode> trackTile_nodes_list) {

        // shuffle to create random order of tile indices
        List<Integer> tile_indices_list = new ArrayList<Integer>();
        for(int i=0; i<24; i++) {
            tile_indices_list.add(i);
        }

        Collections.shuffle(tile_indices_list);

        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/track_map.txt")));
            String[] pos;
            Integer column, row;

            System.out.println("Creating track tiles");

            for (Integer tile_id: tile_indices_list) {
                // read position
                pos = br.readLine().split(" ");
                column = Integer.parseInt(pos[0]);
                row = Integer.parseInt(pos[1]);

                // read image according to tile id
                Image tileImg = new Image(getClass().getResource("/tiles/" + tile_id % 12 + ".png").toExternalForm()); // take modulo since there are 24 tracktiles with 12 octa tiles/images
                ImageView tileImgView = new ImageView(tileImg);
                tileImgView.setFitWidth(50);
                tileImgView.setFitHeight(50);

                // instantiate track tile entity
                TrackTile track_tile = engine.entity_creator.createTrackTile(tile_id, tileImgView);
                FXObject fx_object = new FXObject(tileImgView);
                trackTile_nodes_list.add(this.engine.node_creator.createTrackTileNode(track_tile.position, track_tile.fx_object));
                
                // add to map
                gridPane.add(tileImgView, column, row);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // octa tiles
        List<Integer> octa_indices_list = new ArrayList<Integer>();
        for (int i=0; i<12; i++) {
            octa_indices_list.add(i);
        }

        Collections.shuffle(octa_indices_list);

        try {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/octa_map.txt")));
            String[] pos;
            Integer column, row;

            System.out.println("Creating octagonal tiles");

            for (Integer tile_id: octa_indices_list) {
                // read position
                pos = br.readLine().split(" ");
                column = Integer.parseInt(pos[0]);
                row = Integer.parseInt(pos[1]);

                // instantiate octagonal tile entity
                OctaTile octa_tile = engine.entity_creator.createOctaTile(tile_id);

                // read image according to tile id
                Image tileImg = new Image(getClass().getResource("/tiles/" + tile_id + ".png").toExternalForm());
                ImageView tileImgView = new ImageView(tileImg);
                tileImgView.setFitWidth(50);
                tileImgView.setFitHeight(50);

                // create button for octagonal tiles
                Button button = new Button("");
                button.setGraphic(tileImgView); // set image for button
                octa_tile.setButton(button); // assign button to entity
                
                tile_nodes_list.add(engine.node_creator.createTileNode(octa_tile.position, octa_tile.selected));
                button_nodes_list.add(engine.node_creator.createButtonNode(octa_tile.button));

                // add to map
                gridPane.add(octa_tile.button, column, row);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPlayers(GridPane gridPane, ArrayList<PlayerNode> player_nodes_list) {
        Integer num_player = Settings.PLAYERS;

        Random rand = new Random();
        Integer tile_id = rand.nextInt(24);
        
        int space = 24 / num_player;

        while (--num_player>=0) {
            // read image according to tile id
            Image chickenImg = new Image(getClass().getResource("/chicken/chicken_" + num_player + "/1.png").toExternalForm());
            ImageView chickenImgView = new ImageView(chickenImg);
            chickenImgView.setFitWidth(100);
            chickenImgView.setFitHeight(80);

            Player player = this.engine.entity_creator.createPlayer(tile_id, chickenImgView);
            FXObject fx_object = new FXObject(chickenImgView);
            PlayerNode player_node = this.engine.node_creator.createPlayerNode(player.position, player.feather_list, fx_object);

            // add to map
            gridPane.add(chickenImgView, 4, 4);

            // add to node list and update next position
            player_nodes_list.add(player_node);
            tile_id = (tile_id + space) % 24;
        }
    }

    public GridPane initGame() {
        ArrayList<TileNode> tile_nodes_list = new ArrayList<TileNode>();
        ArrayList<ButtonNode> button_nodes_list = new ArrayList<ButtonNode>();
        ArrayList<PlayerNode> player_nodes_list = new ArrayList<PlayerNode>();
        ArrayList<TrackTileNode> trackTile_nodes_list = new ArrayList<TrackTileNode>();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap between elements
        gridPane.setVgap(10); // Vertical gap between elements

        this.createMap(gridPane, tile_nodes_list, button_nodes_list, trackTile_nodes_list);
        // this.addPlayers(gridPane, player_nodes_list);

        // set LogicSystem
        this.logic_system = new LogicSystem(tile_nodes_list, button_nodes_list, player_nodes_list);
        // this.move_system = new MoveSystem(trackTile_nodes_list, player_nodes_list);

        this.engine.addSystem(this.logic_system);
        // this.engine.addSystem(this.move_system);

        gridPane.setStyle("-fx-alignment: center;");
        
        return gridPane;
    }
}
