package com.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Draft extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane for a table-like structure
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap between elements
        gridPane.setVgap(10); // Vertical gap between elements

        // Create 12 circles in the second row
        for (int i = 0; i < 12; i++) {
            Circle circle = new Circle(20, Color.BLUE); // Circle with radius 20
            gridPane.add(circle, i, 1); // Add circle to row 1, column i
        }

        // Create 12 buttons in the first row
        for (int i = 0; i < 12; i++) {
            Button button = new Button("Button " + (i + 1));
            gridPane.add(button, i, 0); // Add button to row 0, column i
        }

        Image chicken = new Image(getClass().getResource("/chicken/chickenpixel.png").toExternalForm());
        ImageView entityImageView = new ImageView(chicken);

        entityImageView.setFitWidth(50);
        entityImageView.setFitHeight(50);
        gridPane.add(entityImageView, 3, 1);
        // Center the grid pane within the scene
        gridPane.setStyle("-fx-alignment: center;"); // Center alignment

        // Set up the scene
        Scene scene = new Scene(gridPane, 800, 200); // Set the size of the window

        // Set the stage
        primaryStage.setTitle("Button and Circle Grid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
