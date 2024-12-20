package game;

import app.src.main.java.game.core.blueprints.BlueprintRegistry;
import app.src.main.java.game.entities.Feather;
import app.src.main.java.game.components.*;

import java.util.Map;
import java.util.HashMap;

public class Main {
    Map<String, Object> params = new HashMap<>();

    Position position = new Position(0, 0, 0);
    FeatherList feather_list = new FeatherList(new Feather());

    params.put("position", position);
    params.put("feather_list", feather_list);

    BlueprintRegistry.create("Player", params);

    System.out.println("Check");
}
