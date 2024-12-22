package com.entities;

import com.components.Position;
import com.components.TileChoosable;

public class Tile {
    public Position position;
    public TileChoosable choosable;
    // public img;

    public Tile(Position position, TileChoosable choosable) {
        this.position = position;
        this.choosable = choosable;
    }
}