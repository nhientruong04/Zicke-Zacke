package com.components;

import com.entities.Feather;

import java.util.ArrayList;

public class FeatherList extends Component {
    public ArrayList<Feather> feathers;

    public FeatherList(Feather feather) {
        this.feathers = new ArrayList<Feather>();
        this.feathers.add(feather);
    }
}
