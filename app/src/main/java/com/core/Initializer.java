package com.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import com.entities.*;
import com.nodes.*;
import com.systems.*;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Initializer {
    private Engine engine;
    private RenderSystem render_system;
    private LogicSystem logic_system;
    private MoveSystem move_system;
    private TurnHUDDisplaySystem turn_HUD_system;

    public Initializer(Engine engine) {
        this.engine = engine;
    }

    private List<Integer> createTrackTilesLayout(Pane trackTiles_layout, ArrayList<TrackTileNode> trackTile_nodes_list) {
        BufferedReader br;

        // shuffle to create random order of tile indices
        List<Integer> img_indices_list = new ArrayList<Integer>();
        for(int i=0; i<24; i++) {
            img_indices_list.add(i%12);
        }

        Collections.shuffle(img_indices_list);

        // Load and arrange 24 tiles at the center of the map
        int tileRows = 8;
        int tileColumns = 8;
        double tileSize = (Settings.TRACKTILE_HEIGHT_BASE * Settings.TILE_SIZE_SCALE) + 5; // Example size for each tile
        double mapWidth = 1000; // Match the background width
        double mapHeight = 700; // Match the background height

        double startX = (mapWidth - (tileColumns * tileSize)) / 2;
        double startY = (mapHeight - (tileRows * tileSize)) / 2;

        try {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/track_map.txt")));
            String[] pos;
            Integer column, row;

            System.out.println("Creating track tiles");

            for (int tile_id=0; tile_id<img_indices_list.size(); tile_id++) {
                int img_id = img_indices_list.get(tile_id);

                // read position
                pos = br.readLine().split(" ");
                column = Integer.parseInt(pos[0]);
                row = Integer.parseInt(pos[1]);

                // read image according to tile id
                Image tileImg = new Image(getClass().getResource("/track_tiles/" + img_id + ".png").toExternalForm()); // take modulo since there are 24 tracktiles with 12 octa tiles/images
                ImageView tileImgView = new ImageView(tileImg);
                tileImgView.setFitWidth(Settings.TRACKTILE_WIDTH_BASE * Settings.TILE_SIZE_SCALE);
                tileImgView.setFitHeight(Settings.TRACKTILE_HEIGHT_BASE * Settings.TILE_SIZE_SCALE);

                // Position the tile
                tileImgView.setLayoutX(startX + column * tileSize);
                tileImgView.setLayoutY(startY + row * tileSize);

                // instantiate track tile entity
                TrackTile track_tile = engine.entity_creator.createTrackTile(tile_id, img_id, tileImgView);

                trackTile_nodes_list.add(this.engine.node_creator.createTrackTileNode(track_tile.position, track_tile.fx_object));
                
                // add to map
                trackTiles_layout.getChildren().add(track_tile.fx_object.object);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img_indices_list;
    }

    private void createOctagonalTilesLayout(GridPane octaTiles_top_layout, GridPane octaTiles_under_layout, ArrayList<OctaTileNode> octaTile_nodes_list, ArrayList<ButtonNode> button_nodes_list) {
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

            for (Integer img_id: octa_indices_list) {
                // read position
                pos = br.readLine().split(" ");
                column = Integer.parseInt(pos[0]);
                row = Integer.parseInt(pos[1]);

                // image corresponds to id for top tile
                Image topTile_img = new Image(getClass().getResource("/octa_tiles/backside.png").toExternalForm());
                ImageView topTileImgView = new ImageView(topTile_img);
                topTileImgView.setFitWidth(Settings.OCTATILE_WIDTH_BASE * Settings.TILE_SIZE_SCALE);
                topTileImgView.setFitHeight(Settings.OCTATILE_HEIGHT_BASE * Settings.TILE_SIZE_SCALE);

                // image corresponds to id for under tile
                Image underTile_img = new Image(getClass().getResource("/octa_tiles/" + img_id + ".png").toExternalForm());
                ImageView underTileImgView = new ImageView(underTile_img);
                underTileImgView.setFitWidth(Settings.OCTATILE_WIDTH_BASE * Settings.TILE_SIZE_SCALE);
                underTileImgView.setFitHeight(Settings.OCTATILE_HEIGHT_BASE * Settings.TILE_SIZE_SCALE);

                // create button for top tiles
                Button button = new Button("");
                button.setGraphic(topTileImgView); // set top image for button
                

                // instantiate octagonal tile entity
                OctaTile octa_tile = engine.entity_creator.createOctaTile(img_id, topTileImgView);
                // assign button to entity
                octa_tile.setButton(button);
                
                octaTile_nodes_list.add(
                    engine
                    .node_creator
                    .createOctaTileNode(
                        octa_tile.fx_object, 
                        octa_tile.selected)
                );

                button_nodes_list.add(
                    engine
                    .node_creator
                    .createButtonNode(octa_tile.button)
                );

                 // add under layer to map
                 octaTiles_under_layout.add(
                    underTileImgView,
                    column,
                    row
                );

                // add top layer to map
                octaTiles_top_layout.add(
                    octa_tile.button,
                    column,
                    row
                );
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sort for precise indexing in other systems
        octaTile_nodes_list.sort((o1, o2) -> ((Integer) o1.fx_object.img_id).compareTo(o2.fx_object.img_id));
    }

    private void addPlayers(Pane move_layout, GridPane hud_section, ArrayList<PlayerNode> player_nodes_list) {
        // TODO: bullshit player randomization

        Integer num_player = Settings.PLAYERS;
        Image feather_img = new Image(getClass().getResource("/feather/feather.png").toExternalForm());

        Random rand = new Random();
        Integer tile_id = rand.nextInt(24);
        
        int space = 24 / num_player;
        double height = Settings.CHICKEN_HEIGHT_BASE * Settings.CHICKEN_SIZE_SCALE;
        double width = Settings.CHICKEN_WIDTH_BASE * Settings.CHICKEN_SIZE_SCALE;
        int hud_row_idx = 0;

        for (int i=0; i<num_player; i++) {
            // read image according to tile id
            Image player_img = new Image(getClass().getResource("/chicken/chicken_" + i + "/1.png").toExternalForm());
            ImageView playerImgView = Utils.getImageView(player_img);
            playerImgView.setFitWidth(width);
            playerImgView.setFitHeight(height);

            // set HUD for player
            FlowPane hud = new FlowPane(5, 10);
            // initialize and bind size of player image on HUD
            ImageView chicken_hud = Utils.getImageView(player_img);
            chicken_hud.setPreserveRatio(true);
            chicken_hud.fitWidthProperty().bind(hud_section.maxWidthProperty().divide(5));
            chicken_hud.fitHeightProperty().bind(hud_section.maxHeightProperty().divide(4));
            // initialize and bind size of feather image on HUD
            ImageView feather_hud = Utils.getImageView(feather_img);
            feather_hud.setPreserveRatio(true);
            feather_hud.fitWidthProperty().bind(hud.maxWidthProperty().divide(5));
            feather_hud.fitHeightProperty().bind(hud.maxHeightProperty());

            // create player and player node
            Player player = this.engine.entity_creator.createPlayer(tile_id, i, feather_hud, playerImgView);
            PlayerNode player_node = this.engine.node_creator.createPlayerNode(player.position, player.feather_list, player.fx_object);
            hud.getChildren().setAll(player.feather_list.feathers);
            
            // size and style of hud section flowpane
            hud.maxWidthProperty().bind(hud_section.maxWidthProperty().multiply(0.75));
            hud.maxHeightProperty().bind(hud_section.maxHeightProperty().divide(4));
            hud.getStyleClass().add("hud-row");

            // set change listener to flowpane
            player
                .feather_list
                .feathers
                .addListener((ListChangeListener<ImageView>) change -> {
                    hud.getChildren().setAll(player.feather_list.feathers); 
                });

            // add to map
            move_layout.getChildren().add(player.fx_object.object);
            hud_section.add(chicken_hud, 0, hud_row_idx);
            hud_section.add(hud, 1, hud_row_idx);

            // add to node list and update next position
            player_nodes_list.add(player_node);
            tile_id = (tile_id + space) % 24;
            hud_row_idx++;
        }
    }

    public void initGame(StackPane root) {
        ArrayList<OctaTileNode> octaTile_nodes_list = new ArrayList<OctaTileNode>();
        ArrayList<ButtonNode> button_nodes_list = new ArrayList<ButtonNode>();
        ArrayList<PlayerNode> player_nodes_list = new ArrayList<PlayerNode>();
        ArrayList<TrackTileNode> trackTile_nodes_list = new ArrayList<TrackTileNode>();
        List<Integer> trackTile_img_indices;

        Pane trackTiles_layout = new Pane();
        // Bind the Pane's size to the StackPane's size
        trackTiles_layout.prefWidthProperty().bind(root.widthProperty());
        trackTiles_layout.prefHeightProperty().bind(root.heightProperty());

        GridPane octaTiles_top_layout = new GridPane();
        octaTiles_top_layout.setHgap(10); // Horizontal gap between elements
        octaTiles_top_layout.setVgap(10); // Vertical gap between elements
        octaTiles_top_layout.getStyleClass().add("octaTiles_layout");

        GridPane octaTiles_under_layout = new GridPane();
        octaTiles_under_layout.setHgap(10); // Horizontal gap between elements
        octaTiles_under_layout.setVgap(10); // Vertical gap between elements
        octaTiles_under_layout.getStyleClass().add("octaTiles_layout");

        Pane move_layout = new Pane();
        move_layout.setMouseTransparent(true); // Pass mouse events through
        move_layout.getStyleClass().add("move_layout");

        // Player and Feather HUD
        GridPane player_and_feather_HUD = new GridPane();
        Utils.setPlayerFeatherHUDSettings(player_and_feather_HUD, root);

        // Turn and Tile HUD
        GridPane turn_and_tile_HUD = new GridPane();
        Utils.setTurnTileHUDSettings(turn_and_tile_HUD, root);

        // BorderPane for huds
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(player_and_feather_HUD);
        borderPane.setLeft(turn_and_tile_HUD);

        // create track tiles and get img indices
        trackTile_img_indices = new ArrayList<Integer>(this.createTrackTilesLayout(trackTiles_layout, trackTile_nodes_list));
        this.createOctagonalTilesLayout(octaTiles_top_layout, octaTiles_under_layout, octaTile_nodes_list, button_nodes_list);
        this.addPlayers(move_layout, player_and_feather_HUD, player_nodes_list);

        // set LogicSystem
        this.logic_system = new LogicSystem(octaTile_nodes_list, button_nodes_list, player_nodes_list, trackTile_nodes_list);
        this.move_system = new MoveSystem(trackTile_nodes_list, player_nodes_list);
        this.turn_HUD_system = new TurnHUDDisplaySystem(player_nodes_list, turn_and_tile_HUD, trackTile_img_indices);

        this.engine.addSystem(this.logic_system);
        this.engine.addSystem(this.move_system);
        this.engine.addSystem(this.turn_HUD_system);

        root.getChildren().addAll(borderPane, trackTiles_layout, octaTiles_under_layout, octaTiles_top_layout, move_layout); // add according to order
    }
}
