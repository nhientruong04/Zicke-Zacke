package com.systems;

import javafx.util.Duration;
import java.util.ArrayList;

import com.core.Settings;
import com.nodes.PlayerNode;
import com.nodes.TrackTileNode;

import javafx.animation.PathTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.QuadCurve;

public class MoveSystem extends ISystem {
    private ArrayList<TrackTileNode> trackTile_nodes;
    private ArrayList<PlayerNode> player_nodes;

    public MoveSystem(ArrayList<TrackTileNode> trackTile_nodes, ArrayList<PlayerNode> player_nodes) {
        this.trackTile_nodes = trackTile_nodes;
        this.player_nodes = player_nodes;

        this.initial_position();
    }

    private void initial_position() {
        for (int i=0; i<this.player_nodes.size(); i++) {
            PlayerNode player_node = this.player_nodes.get(i);
            int dest_tileId = player_node.position.tile_id; // get the presumed position
            
            ImageView trackTileView = this.trackTile_nodes.get(dest_tileId).fx_object.object;
            System.out.println("Tile id: " + dest_tileId + ", object " + trackTileView);

            player_node.fx_object.object.setLayoutX(trackTileView.getLayoutX() - Settings.CHICKEN_PADDING_X);
            player_node.fx_object.object.setLayoutY(trackTileView.getLayoutY() - Settings.CHICKEN_PADDING_Y);
        }
    }

    @Override
    public void update() {
        for (int i=0; i<this.player_nodes.size(); i++) {
            PlayerNode player_node = this.player_nodes.get(i);
            int dest_tileId = player_node.position.tile_id; // get the presumed position

            ImageView trackTileView = this.trackTile_nodes.get(dest_tileId).fx_object.object;

            double targetX = trackTileView.getLayoutX() - Settings.CHICKEN_PADDING_X;
            double startX = player_node.fx_object.object.getLayoutX();
            double targetY = trackTileView.getLayoutY() - Settings.CHICKEN_PADDING_Y;
            double startY = player_node.fx_object.object.getLayoutY();

            // TODO: incorrect condition
            if (startX!=targetX | startY!=targetY) {
                // QuadCurve curve = new QuadCurve(
                // startX, startY, // Start point
                // (startX+targetX)/2, (startY+targetY)/2, // Control point (curve peak)
                // targetX, targetY // End point
                // );
                // // curve.setStroke(Color.BLUE);
                // // curve.setFill(Color.BLACK);

                // // Create a PathTransition
                // PathTransition pathTransition = new PathTransition();
                // pathTransition.setNode(player_node.fx_object.object);
                // pathTransition.setPath(curve);
                // pathTransition.setDuration(Duration.seconds(1)); // Duration: 1 second
                // pathTransition.setCycleCount(1); // Play once
                // pathTransition.setAutoReverse(false); // No reverse motion
                
                // // Start the animation
                // pathTransition.play();

                player_node.fx_object.object.setLayoutX(targetX);
                player_node.fx_object.object.setLayoutY(targetY);
            }
        }
    }
}
