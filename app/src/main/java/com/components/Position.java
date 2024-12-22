package com.components;

public class Position extends Component {
    public int tile_id;
    public int x;
    public int y;

    public Position (int tile_id, int x, int y) {
        this.tile_id = tile_id;
        this.x = x;
        this.y= y;
    }
}
