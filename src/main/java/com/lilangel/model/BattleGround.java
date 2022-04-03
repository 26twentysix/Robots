package com.lilangel.model;

import com.lilangel.presenter.ModelListener;

import java.util.ArrayList;

/**
 * Class that describes the model of battleground
 */
public class BattleGround implements Model {
    /**
     * Presenter that model connected with
     */
    ModelListener presenter;
    /**
     * Battleground with objects on it
     */
    ObjectOnTile[][] battleground;
    private final int height = 200;
    private final int width = 300;
    /**
     * List of robots
     */
    private ArrayList<Robot> robots;
    private final int robotsCount = 20; //пока 20, потом посмотрим, может можно больше

    public BattleGround() {
        this.battleground = new ObjectOnTile[width][height];
        this.robots = new ArrayList<>(robotsCount);
        for (int i = 0;i< width;i++)
            for(int j = 0; j<height;j++)
                battleground[i][j] = ObjectOnTile.EMPTY;
    }

    @Override
    public void notifyPresenter() {

    }

    @Override
    public void setPresenter(ModelListener presenter) {
        this.presenter = presenter;
    }

    /**
     * Method that forces robots into action
     */
    public void startFight() {

    }

    /**
     * Method that returns object on tile at cooridnates [x,y]
     * @param x x coordinate
     * @param y y coordinate
     * @return instance of {@link ObjectOnTile} that is on the tile
     */
    ObjectOnTile getTile(int x, int y) {
        return battleground[x][y];
    }
}
