package app.src.main.java.game.entities;

import app.src.main.java.game.components.Position;
import app.src.main.java.game.components.TileChoosable;

public class Tile {
    public Position position;
    public TileChoosable choosable;
    // public img;

    public Tile(Position position, TileChoosable choosable) {
        this.position = position;
        this.choosable = choosable;
    }
}