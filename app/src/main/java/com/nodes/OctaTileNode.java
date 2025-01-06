package com.nodes;

import com.components.FXObject;
import com.components.TileSelectedState;

public class OctaTileNode extends Node {
    public FXObject fx_object;
    public TileSelectedState selected;

    public OctaTileNode (FXObject fx_object, TileSelectedState selected) {
        this.fx_object = fx_object;
        this.selected = selected; // init don't need to declare this
    }
}
