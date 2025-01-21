package com.core;

import java.util.ArrayList;

import com.nodes.PlayerNode;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class Utils {
    public static ImageView getImageView(Image img) {
        ImageView img_view = new ImageView(img);

        return img_view;
    }

    public static int getPlayerNextRawTileId(ArrayList<PlayerNode> player_nodes) {
        ArrayList<PlayerNode> player_nodes_cloned = new ArrayList<PlayerNode>(player_nodes);

        int turn_index = 0;

        // will be a huge bug if player turns are not set correctly, e.g no player has the turn
        while (turn_index<player_nodes.size()) {
            if (player_nodes.get(turn_index).inTurn) {
                break;
            }

            turn_index++;
        }

        int playerTileId = player_nodes.get(turn_index).position.tile_id;
        int nextTileId = playerTileId + 1;
        
        player_nodes_cloned.remove(turn_index); // remove current player
        player_nodes_cloned.sort((o1, o2) -> ((Integer) o1.position.tile_id).compareTo(o2.position.tile_id));
        
        int player_ind = 0;

        while (player_ind<Settings.PLAYERS-1) {
            if (player_nodes_cloned.get(player_ind).position.tile_id==(nextTileId%24)) {
                nextTileId++;
                player_ind = -1;
            }

            player_ind++;
        }

        return nextTileId; // before taking modulo of 24
    }

    public static void setPlayerFeatherHUDSettings(GridPane gridPane, StackPane parent) {
        gridPane.setPadding(new Insets(5));
        gridPane.maxWidthProperty().bind(parent.widthProperty().multiply(0.3));
        gridPane.maxHeightProperty().bind(parent.heightProperty().divide(4));
        gridPane.getStyleClass().add("hud-container");

        // Add column constraints to make columns fill available space
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25); // 25% of the total width
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(75); // 75% of the total width
        gridPane.getColumnConstraints().addAll(col1, col2);

        // Add row constraints to make rows fill available space
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(25); // 25% of the total height
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25); // 25% of the total height
        gridPane.getRowConstraints().addAll(row1, row2);
        RowConstraints row3 = new RowConstraints();
        row1.setPercentHeight(25); // 25% of the total height
        RowConstraints row4 = new RowConstraints();
        row2.setPercentHeight(25); // 25% of the total height
        gridPane.getRowConstraints().addAll(row1, row2, row3, row4);
    }

    public static void setTurnTileHUDSettings(GridPane gridPane, StackPane parent) {
        gridPane.setPadding(new Insets(5));
        gridPane.maxWidthProperty().bind(parent.widthProperty().multiply(0.28));
        gridPane.maxHeightProperty().bind(parent.heightProperty().multiply(0.3));

        // Add column constraints to make columns fill available space
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50); // 50% of the total width
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50); // 50% of the total width
        gridPane.getColumnConstraints().addAll(col1, col2);

        // Add row constraints to make rows fill available space
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(30); // 30% of the total height
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(70); // 70% of the total height
        gridPane.getRowConstraints().addAll(row1, row2);

        // Add "Turn" Label
        Label turn_label = new Label("Turn");
        turn_label.prefWidthProperty().bind(gridPane.widthProperty().multiply(0.45));
        turn_label.prefHeightProperty().bind(gridPane.heightProperty().multiply(0.3));
        gridPane.add(turn_label, 0, 0);

        // Add "Next Tile" Label
        Label tile_label = new Label("Next Tile");
        tile_label.prefWidthProperty().bind(gridPane.widthProperty().multiply(0.45));
        tile_label.prefHeightProperty().bind(gridPane.heightProperty().multiply(0.3));
        gridPane.add(tile_label, 1, 0);
    }
}
