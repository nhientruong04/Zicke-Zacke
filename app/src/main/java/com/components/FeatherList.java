package com.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

public class FeatherList extends Component {
    public ObservableList<ImageView> feathers;

    public FeatherList(ImageView feather) {
        this.feathers = FXCollections.observableArrayList();
        this.feathers.add(feather);
    }
}
