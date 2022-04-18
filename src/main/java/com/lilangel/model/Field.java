package com.lilangel.model;

import java.util.*;

public class Field {
    private ObjectOnTile[][] field;
    private ObjectOnTile[][] previousState;
    private final int width = ModelConstants.FIELD_WIDTH.value;
    private final int height = ModelConstants.FIELD_HEIGT.value;

    private Map<Coordinates, Robot> robotsMapping;
    private ArrayList<Robot> robots;
    private final int robotsLimit = ModelConstants.ROBOTS_COUNT.value;

    private boolean hasChanged;

    ObjectOnTile[][] getField() {
        return this.field;
    }

    ObjectOnTile getTile(int x, int y) {
        if (x < 0)
            x += width;
        if (x >= width)
            x -= width;
        if (y < 0)
            y += height;
        if (y >= height)
            y -= height;

        return field[y][x];
    }

    void setTile(int x, int y, ObjectOnTile obj) {
        if (x < 0)
            x += width;
        if (x >= width)
            x -= width;
        if (y < 0)
            y += height;
        if (y >= height)
            y -= height;

        this.field[y][x] = obj;
    }

    boolean isAnyRobotAlive() {
        return !robots.isEmpty();
    }

    Robot getRobot(int x, int y) {
        return robotsMapping.get(new Coordinates(x, y));
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public Field() {
        this.field = new ObjectOnTile[height][width];
        this.robots = new ArrayList<>();
        this.robotsMapping = new HashMap<>();

        summonRobots();
        fillField();
        this.hasChanged = false;
    }

    private void summonRobots() {
        for (int i = 0; i < robotsLimit; i++) {
            Robot robot = new Robot(this);
            robots.add(robot);
            field[robot.getPositionY()][robot.getPositionX()] = ObjectOnTile.ROBOT;
            robotsMapping.put(new Coordinates(robot.getPositionX(), robot.getPositionY()), robot);
        }
    }

    private void fillField() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (field[i][j] != ObjectOnTile.ROBOT)
                    field[i][j] = generateCell();
    }

    private ObjectOnTile generateCell() {
        Random random = new Random();
        int c = random.nextInt(100);
        ObjectOnTile result = ObjectOnTile.EMPTY;
        if (c > 95 || c < 5) result = ObjectOnTile.ENERGY;
        else if (c > 45 && c < 55) result = ObjectOnTile.WALL;
        return result;
    }

    public void doTact() {
        hasChanged = false;

        previousState = makeCopyOfCurrentState();

        ArrayList<Robot> aliveList = new ArrayList<>();
        for (Robot robot : robots) {
            robotsMapping.remove(new Coordinates(robot.getPositionX(), robot.getPositionY()));
            robot.performAction();
            if (robot.isAlive()) aliveList.add(robot);
            robotsMapping.put(new Coordinates(robot.getPositionX(), robot.getPositionY()), robot);
        }
        this.robots = aliveList;

//        hasChanged = hasFieldChanged();
    }

    public ObjectOnTile[][] makeCopyOfCurrentState(){
        var result = new ObjectOnTile[height][width];
        for(int i = 0; i < height; i++)
            System.arraycopy(field[i], 0, result[i], 0, width);
        return result;
    }

    public String hasFieldChanged() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                if (previousState[i][j] != field[i][j])
                    return "yes: " + i +";"+ j + " " + previousState[i][j] + " --> " + field[i][j];
//                    return true;
            }
        return "no";
//        return false;
    }
}
