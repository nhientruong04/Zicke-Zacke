package com.core;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Utils {
    public static ImageView getImageView(Image img) {
        ImageView img_view = new ImageView(img);

        return img_view;
    }
}
