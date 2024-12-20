package game.core.blueprints;

import game.entities.Player;
import game.components.Position;
import game.components.FeatherList;
import java.util.Map;

public class PlayerBlueprint implements Blueprint<Player>  {

    @Override
    public Player create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        FeatherList feather_list = (FeatherList) params.get("feather_list");

        return new Player(position, feather_list);
    }
}