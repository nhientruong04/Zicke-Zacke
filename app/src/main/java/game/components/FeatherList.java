package game.components;

import java.util.ArrayList;

import game.entities.Feather;

public class FeatherList extends Component {
    public ArrayList<Feather> feathers;

    public FeatherList(Feather feather) {
        this.feathers = new ArrayList<Feather>();
        this.feathers.add(feather);
    }
}
