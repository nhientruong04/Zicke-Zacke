package game.core;

import game.components.Position;
import game.components.TileChoosable;
import game.core.blueprints.BlueprintRegistry;
import game.entities.Tile;

import java.util.Map;
import java.util.HashMap;

public class EntityCreator {

    public Tile createTrackTile(int track_id, int x, int y) {
        Map<String, Object> params = new HashMap<String, Object>();
        Position position = new Position(track_id, x, y);
        TileChoosable choosable = new TileChoosable();

        params.put("position", position);
        params.put("choosable", choosable);

        return BlueprintRegistry.create("Tile", params);
    }

    public Tile createOctaTile(int track_id, int x, int y) {
        Map<String, Object> params = new HashMap<String, Object>();
        Position position = new Position(track_id, x, y);

        params.put("position", position);

        return BlueprintRegistry.create("Tile", params);
    }
}
