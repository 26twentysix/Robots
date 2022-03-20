package com.lilangel.model;

import java.util.ArrayList;

public class ModelState {
    private final ArrayList<Robot> robots;

    public ModelState(ArrayList<Robot> robots){
        this.robots=robots;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }
}
