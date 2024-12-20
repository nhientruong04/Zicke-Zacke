package app.src.main.java.game.components;

import java.util.ArrayList;

import app.src.main.java.game.entities.Feather;

public class FeatherList {
    public ArrayList<Feather> feathers;

    public FeatherList(Feather feather) {
        this.feathers = new ArrayList<Feather>();
        this.feathers.add(feather);
    }
}
