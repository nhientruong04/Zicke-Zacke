package app.src.main.java.game.core.blueprints;

import app.src.main.java.game.entities.Player;
import app.src.main.java.game.components.Position;
import app.src.main.java.game.components.FeatherList;
import java.util.Map;

public class PlayerBlueprint implements Blueprint<Player>  {

    @Override
    public Player create(Map<String, Object> params) {
        Position position = (Position) params.get("position");
        FeatherList feather_list = (FeatherList) params.get("feather_list");

        return new Player(position, feather_list);
    }
}