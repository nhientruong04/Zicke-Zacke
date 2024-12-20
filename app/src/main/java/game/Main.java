package game;

import game.core.blueprints.BlueprintRegistry;
import game.entities.Feather;
import game.components.*;

import java.util.Map;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<String, Object>();

        Position position = new Position(0, 0, 0);
        FeatherList feather_list = new FeatherList(new Feather());

        params.put("position", (Component) position);
        params.put("feather_list", feather_list);

        BlueprintRegistry.create("Player", params);

        System.out.println("Check");   
    }
}
