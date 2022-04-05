package com.lilangel.model;

import com.lilangel.presenter.ModelListener;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class that describes the model of battleground
 */
public class Battleground implements Model {
    /**
     * Presenter that model connected with
     */
    ModelListener presenter;
    /**
     * Field with objects on it
     */
    ObjectOnTile[][] field;

    public ObjectOnTile[][] getField() {
        return field;
    }

    private final int height = 200;
    private final int width = 300;
    /**
     * List of robots
     */
    private CopyOnWriteArrayList<Robot> robots;
    private final int robotsCount = 100; //пока 20, потом посмотрим, может можно больше
    private final int robotConstant = 57; //нужна для работы генератора боевого поля, взята из воздуха

    public Battleground() {
        this.robots = new CopyOnWriteArrayList<>();
        this.field = new ObjectOnTile[width][height];
        for (ObjectOnTile[] objectOnTiles : field) Arrays.fill(objectOnTiles, ObjectOnTile.EMPTY);
        for (int i = 0; i < robotsCount; i++)
            this.robots.add(new Robot(this));
        fillField();
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
        while (!robots.isEmpty()) {
            for (var robot : robots) {
                robot.prepareAction();//это так потому что изначально задумка была каждого совать в отдельный поток,
                robot.doPreparedAction();//ждать пока каждый приготовит действие, потом каждый их исполняет, но они и так работают быстро
            }
//            notifyPresenter();
        }
    }

    public void fillField() {
        for (int i = 0; i < width; i++) {
            field[i][0] = ObjectOnTile.EMPTY;
            field[i][height - 1] = ObjectOnTile.EMPTY;
        }
        for (int i = 0; i < height; i++) {
            field[0][i] = ObjectOnTile.EMPTY;
            field[width - 1][i] = ObjectOnTile.EMPTY;
        }
        int counter = 0;
        Random rnd = new Random();
        for (int i = 1; i < width - 1; i++)
            for (int j = 1; j < height - 1; j++) {
                int randomInt = rnd.nextInt(100);
                if (randomInt == robotConstant) {
                    counter++;
                    if (counter > robotsCount)
                        continue;
                    robots.get(counter - 1).changePosition(i, j);
                }
                field[i][j] = getObjectFromInt(randomInt);
            }
    }

    private ObjectOnTile getObjectFromInt(int i) {
        if (i == robotConstant)
            return ObjectOnTile.ROBOT;
        else if (i < 5 || i > 95)
            return ObjectOnTile.WALL;
        else if (i > 5 && i < 10)
            return ObjectOnTile.ENERGY;
        else return ObjectOnTile.EMPTY;
    }

    void killRobot(int x, int y) {
        Robot robotIdenticalToNeededOne = new Robot(this);
        robotIdenticalToNeededOne.changePosition(x, y);
        robots.remove(robotIdenticalToNeededOne);
        setTileEmpty(x, y);
    }

    void setTileEmpty(int x, int y) {
        field[x][y] = ObjectOnTile.EMPTY;
    }
}
