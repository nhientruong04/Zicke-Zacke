package com.components;

import javafx.scene.image.ImageView;

public class FXObject extends Component {
    public ImageView object;
    public int img_id;

    public FXObject(int img_id, ImageView object) {
        this.object = object;
        this.img_id = img_id;
    }
}
