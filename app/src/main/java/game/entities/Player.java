package app.src.main.java.game.entities;

import app.src.main.java.game.components.Position;
import app.src.main.java.game.components.FeatherList;

public class Player {
    public Position position;    
    public FeatherList feather_list;

    public Player(Position position, FeatherList feather_list) {
        this.position = position;
        this.feather_list = feather_list;
    }
}