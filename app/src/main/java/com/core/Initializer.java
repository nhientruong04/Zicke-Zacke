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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

public class Initializer {
    private Engine engine;
    private RenderSystem render_system;
    private LogicSystem logic_system;
    private MoveSystem move_system;

    public Initializer(Engine engine) {
        this.engine = engine;
    }

    private void createMap(Pane map_layout, ArrayList<TrackTileNode> trackTile_nodes_list) {
        BufferedReader br;

        // shuffle to create random order of tile indices
        List<Integer> tile_indices_list = new ArrayList<Integer>();
        for(int i=0; i<24; i++) {
            tile_indices_list.add(i);
        }

        Collections.shuffle(tile_indices_list);

        // Load and arrange 24 tiles at the center of the map
        int tileRows = 8;
        int tileColumns = 8;
        double tileSize = (Settings.TILE_HEIGHT_BASE * Settings.TILE_SIZE_SCALE) + 5; // Example size for each tile
        double mapWidth = 1000; // Match the background width
        double mapHeight = 700; // Match the background height

        double startX = (mapWidth - (tileColumns * tileSize)) / 2;
        double startY = (mapHeight - (tileRows * tileSize)) / 2;

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
                tileImgView.setFitWidth(Settings.TILE_WIDTH_BASE * Settings.TILE_SIZE_SCALE);
                tileImgView.setFitHeight(Settings.TILE_HEIGHT_BASE * Settings.TILE_SIZE_SCALE);

                // Position the tile
                tileImgView.setLayoutX(startX + column * tileSize);
                tileImgView.setLayoutY(startY + row * tileSize);

                // instantiate track tile entity
                TrackTile track_tile = engine.entity_creator.createTrackTile(tile_id, tileImgView);

                trackTile_nodes_list.add(this.engine.node_creator.createTrackTileNode(track_tile.position, track_tile.fx_object));
                
                // add to map
                map_layout.getChildren().add(track_tile.fx_object.object);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sort for precise indexing in other systems
        trackTile_nodes_list.sort((o1, o2) -> ((Integer) o1.position.tile_id).compareTo(o2.position.tile_id));
    }

    private void createOctagonalTilesLayout (GridPane octaTiles_layout, ArrayList<TileNode> tile_nodes_list, ArrayList<ButtonNode> button_nodes_list) {
        BufferedReader br;

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
                tileImgView.setFitWidth(Settings.TILE_WIDTH_BASE * Settings.TILE_SIZE_SCALE);
                tileImgView.setFitHeight(Settings.TILE_HEIGHT_BASE * Settings.TILE_SIZE_SCALE);

                // create button for octagonal tiles
                Button button = new Button("");
                button.setGraphic(tileImgView); // set image for button
                octa_tile.setButton(button); // assign button to entity
                
                tile_nodes_list.add(engine.node_creator.createTileNode(octa_tile.position, octa_tile.selected));
                button_nodes_list.add(engine.node_creator.createButtonNode(octa_tile.button));

                // add to map
                octaTiles_layout.add(octa_tile.button, column, row);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sort for precise indexing in other systems
        tile_nodes_list.sort((o1, o2) -> ((Integer) o1.position.tile_id).compareTo(o2.position.tile_id));
    }

    private void addPlayers(Pane move_layout, ArrayList<PlayerNode> player_nodes_list) {
        Integer num_player = Settings.PLAYERS;

        Random rand = new Random();
        Integer tile_id = rand.nextInt(24);
        
        int space = 24 / num_player;

        while (--num_player>=0) {
            // read image according to tile id
            Image chickenImg = new Image(getClass().getResource("/chicken/chicken_" + num_player + "/1.png").toExternalForm());
            ImageView chickenImgView = new ImageView(chickenImg);
            chickenImgView.setFitWidth(Settings.CHICKEN_WIDTH_BASE * Settings.CHICKEN_SIZE_SCALE);
            chickenImgView.setFitHeight(Settings.CHICKEN_HEIGHT_BASE * Settings.CHICKEN_SIZE_SCALE);

            Player player = this.engine.entity_creator.createPlayer(tile_id, chickenImgView);
            FXObject fx_object = new FXObject(chickenImgView);
            PlayerNode player_node = this.engine.node_creator.createPlayerNode(player.position, player.feather_list, fx_object);

            // add to map
            move_layout.getChildren().add(chickenImgView);

            // add to node list and update next position
            player_nodes_list.add(player_node);
            tile_id = (tile_id + space) % 24;
        }
    }

    public void initGame(StackPane root) {
        ArrayList<TileNode> tile_nodes_list = new ArrayList<TileNode>();
        ArrayList<ButtonNode> button_nodes_list = new ArrayList<ButtonNode>();
        ArrayList<PlayerNode> player_nodes_list = new ArrayList<PlayerNode>();
        ArrayList<TrackTileNode> trackTile_nodes_list = new ArrayList<TrackTileNode>();

        Pane map_layout = new Pane();
        // Bind the Pane's size to the StackPane's size
        map_layout.prefWidthProperty().bind(root.widthProperty());
        map_layout.prefHeightProperty().bind(root.heightProperty());

        GridPane octaTiles_layout = new GridPane();
        octaTiles_layout.setHgap(10); // Horizontal gap between elements
        octaTiles_layout.setVgap(10); // Vertical gap between elements
        octaTiles_layout.getStyleClass().add("octaTiles_layout");

        Pane move_layout = new Pane();
        move_layout.setMouseTransparent(true); // Pass mouse events through
        move_layout.getStyleClass().add("move_layout");

        this.createMap(map_layout, trackTile_nodes_list);
        this.createOctagonalTilesLayout(octaTiles_layout, tile_nodes_list, button_nodes_list);
        this.addPlayers(move_layout, player_nodes_list);

        // set LogicSystem
        this.logic_system = new LogicSystem(tile_nodes_list, button_nodes_list, player_nodes_list);
        this.move_system = new MoveSystem(trackTile_nodes_list, player_nodes_list);

        this.engine.addSystem(this.logic_system);
        // this.engine.addSystem(this.move_system);

        root.getChildren().addAll(map_layout, octaTiles_layout, move_layout); // add according to order
    }
}
