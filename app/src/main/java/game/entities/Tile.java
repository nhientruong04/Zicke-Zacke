package game.entities;

import game.components.Position;
import game.components.TileChoosable;

public class Tile {
    public Position position;
    public TileChoosable choosable;
    // public img;

    public Tile(Position position, TileChoosable choosable) {
        this.position = position;
        this.choosable = choosable;
    }
}