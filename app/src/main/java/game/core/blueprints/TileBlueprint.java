package game.core.blueprints;

import game.components.Position;
import game.components.TileChoosable;
import game.entities.Tile;

import java.util.Map;

public class TileBlueprint implements Blueprint<Tile> {

    @Override
    public Tile create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        TileChoosable choosable = (TileChoosable) params.getOrDefault("choosable", null);

        return new Tile(position, choosable);
    }
}