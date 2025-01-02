package com.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import com.entities.OctaTile;
import com.entities.Tile;
import com.entities.TrackTile;
import com.nodes.ButtonNode;
import com.nodes.RenderNode;
import com.nodes.TileNode;
import com.systems.RenderSystem;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Initializer {
    Engine engine;
    RenderSystem render_system;

    public Initializer(Engine engine) {
        this.engine = engine;
    }

    public GridPane initMap() {
        ArrayList<TileNode> tile_nodes_list = new ArrayList<TileNode>();
        ArrayList<ButtonNode> button_nodes_list = new ArrayList<ButtonNode>();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap between elements
        gridPane.setVgap(10); // Vertical gap between elements

        // tile indices
        Integer[] indices_array = {0,1,2,3,4,5,6,7,8,9,10,11};

        // shuffle to create random order of tile indices
        List<Integer> tile_indices_list = new ArrayList<Integer>(Arrays.asList(indices_array));
        tile_indices_list.addAll(tile_indices_list);
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

                // instantiate track tile entity
                TrackTile track_tile = engine.entity_creator.createTrackTile(tile_id, column, row);

                // read image according to tile id
                Image tileImg = new Image(getClass().getResource("/tiles/" + tile_id + ".png").toExternalForm());
                ImageView tileImgView = new ImageView(tileImg);
                tileImgView.setFitWidth(50);
                tileImgView.setFitHeight(50);
                
                // add to map
                gridPane.add(tileImgView, column, row);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // octa tiles
        List<Integer> octa_indices_list = Arrays.asList(indices_array);
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
                OctaTile octa_tile = engine.entity_creator.createOctaTile(tile_id, column, row);

                // read image according to tile id
                Image tileImg = new Image(getClass().getResource("/tiles/" + tile_id + ".png").toExternalForm());
                ImageView tileImgView = new ImageView(tileImg);
                tileImgView.setFitWidth(50);
                tileImgView.setFitHeight(50);
    
                Button button = new Button("" + tile_id);
                button.setGraphic(tileImgView);
                octa_tile.setButton(button);
                
                tile_nodes_list.add(engine.node_creator.createTileNode(octa_tile.position, octa_tile.selected));
                button_nodes_list.add(engine.node_creator.createButtonNode(octa_tile.button));

                // add to map
                gridPane.add(tileImgView, column, row);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gridPane.setStyle("-fx-alignment: center;");

        return gridPane;
    }
}
