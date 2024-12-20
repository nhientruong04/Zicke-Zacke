package game.core.blueprints;

import game.entities.Feather;
import java.util.Map;

public class FeatherBlueprint implements Blueprint<Feather>  {

    @Override
    public Feather create(Map<String, Object> params) {
        return new Feather();
    }
}