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

            Bounds targetBounds = trackTileView.localToScene(trackTileView.getBoundsInParent());

            System.out.println(targetBounds.getMinX() + " " + targetBounds.getMinY());

            player_node.fx_object.object.setLayoutX(targetBounds.getMinX());
            player_node.fx_object.object.setLayoutY(targetBounds.getMinY());
        }
    }

    @Override
    public void update() {
        for (int i=0; i<this.player_nodes.size(); i++) {
            PlayerNode player_node = this.player_nodes.get(i);
            int dest_tileId = player_node.position.tile_id; // get the presumed position

            ImageView trackTileView = this.trackTile_nodes.get(dest_tileId).fx_object.object;

            double targetX = trackTileView.getLayoutX();
            double startX = player_node.fx_object.object.getLayoutX();
            // TODO: incorrect condition
            if (startX!=targetX) {
                double targetY = trackTileView.getLayoutY() - Settings.STAND_PADDING;
                double startY = player_node.fx_object.object.getLayoutY();

                QuadCurve curve = new QuadCurve(
                startX, startY, // Start point
                (startX+targetX)/2, (startY+targetY)/2, // Control point (curve peak)
                targetX, targetY // End point
                );
                // curve.setStroke(Color.BLUE);
                // curve.setFill(Color.BLACK);

                // Create a PathTransition
                PathTransition pathTransition = new PathTransition();
                pathTransition.setNode(player_node.fx_object.object);
                pathTransition.setPath(curve);
                pathTransition.setDuration(Duration.seconds(1)); // Duration: 1 second
                pathTransition.setCycleCount(1); // Play once
                pathTransition.setAutoReverse(false); // No reverse motion
                
                // Start the animation
                pathTransition.play();
            }
        }
    }
}
