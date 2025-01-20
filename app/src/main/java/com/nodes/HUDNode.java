package com.nodes;

import com.components.FeatherList;
import com.components.FeathersHUD;

public class HUDNode extends Node {
    public FeathersHUD hud;
    public FeatherList feather_list;

    public HUDNode(FeathersHUD hud, FeatherList feather_list) {
        this.hud = hud;
        this.feather_list = feather_list;
    }
}
