package com.lilangel.model;

import com.lilangel.presenter.ModelListener;

import java.util.ArrayList;

/**
 * Class that describes the model of battleground and the robots
 */
public class Model {
    ModelListener presenter;
    private double[][] battleGround;
    private final ArrayList<Robot> robots;
    private final int robotsCount = 20; //пока 20, потом посмотрим, может можно больше

    public Model(ModelListener presenter) {
        this.presenter = presenter;
        this.battleGround = new double[150][150];
        this.robots = new ArrayList<>(robotsCount);
        for(int i =0; i<robotsCount;i++)
            robots.add(new Robot());
    }

    public void startFight() {

    }

    public ModelState getState(){
        return new ModelState(robots);
    }

    public double[][] getBattleGround() {
        return battleGround;
    }

    private void setBattleGround(double[][] battleGround) {
        this.battleGround = battleGround;
    }
}
