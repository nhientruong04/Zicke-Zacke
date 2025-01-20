package com.components;

import javafx.scene.layout.FlowPane;

public class FeathersHUD extends Component {
    public FlowPane feathers_placeholder;
    public int feather_num;

    public FeathersHUD(FlowPane feathers_placeholder) {
        this.feathers_placeholder = feathers_placeholder;
        this.feather_num = feathers_placeholder.getChildren().size();
    }
}
