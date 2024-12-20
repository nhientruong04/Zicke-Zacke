package app.src.main.java.game.core.blueprints;

import app.src.main.java.game.components.Position;
import app.src.main.java.game.components.TileChoosable;
import app.src.main.java.game.entities.Tile;

import java.util.Map;

public class TileBlueprint implements Blueprint<Tile> {

    @Override
    public Tile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        TileChoosable choosable = (TileChoosable) params.getOrDefault("choosable", null);

        return new Tile(position, choosable);
    }
}