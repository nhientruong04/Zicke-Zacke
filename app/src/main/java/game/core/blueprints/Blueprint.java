package app.src.main.java.game.core.blueprints;

import java.util.Map;

public interface Blueprint<T> {
    T create(Map <String, Object> params); // Creates and returns an instance of the entity or system
}
