package com.lilangel.models;

import com.lilangel.models.actions.RobotAction;

import java.util.*;

public class Field {
    private final ObjectOnTile[][] field;
    private ObjectOnTile[][] previousState;
    private final int width = ModelConstants.FIELD_WIDTH.value;
    private final int height = ModelConstants.FIELD_HEIGT.value;

    private final Map<Coordinates, Robot> robotsMapping;
    private ArrayList<Robot> robots;
    private final int robotsLimit = ModelConstants.ROBOTS_COUNT.value;

    private boolean hasChanged;

    ObjectOnTile[][] getField() {
        return this.field;
    }

    public ObjectOnTile getTile(int x, int y) {
        Coordinates normalized = normalize(x,y);

        return field[normalized.yPos()][normalized.xPos()];
    }

    public void setTile(int x, int y, ObjectOnTile obj) {
        Coordinates normalized = normalize(x,y);

        this.field[normalized.yPos()][normalized.xPos()] = obj;
    }

    private Coordinates normalize(int x, int y){
        while (x < 0)
            x += width;
        while (x >= width)
            x -= width;
        while (y < 0)
            y += height;
        while (y >= height)
            y -= height;
        return new Coordinates(x, y);
    }

    boolean isAnyRobotAlive() {
        return !robots.isEmpty();
    }

    public Robot getRobot(int x, int y) {
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

            Robot robot = new Robot(getEmtpyCell());
            robots.add(robot);
            field[robot.getPositionY()][robot.getPositionX()] = ObjectOnTile.ROBOT;
            robotsMapping.put(new Coordinates(robot.getPositionX(), robot.getPositionY()), robot);
        }
    }

    private Coordinates getEmtpyCell(){
        Random random = new Random();
        int xPos = random.nextInt(ModelConstants.FIELD_WIDTH.value);
        int yPos = random.nextInt(ModelConstants.FIELD_HEIGT.value);
        ObjectOnTile obj = field[yPos][xPos];
        while(obj!=ObjectOnTile.EMPTY && obj!=null){
            xPos = random.nextInt(ModelConstants.FIELD_WIDTH.value);
            yPos = random.nextInt(ModelConstants.FIELD_HEIGT.value);
            obj = field[yPos][xPos];
        }
        return new Coordinates(xPos,yPos);
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
            int iterations = 0;
            while(robot.Active()){
                RobotAction action = robot.prepareAction();
                action.handle(robot,this);
                iterations++;
                if(iterations>9) break;
            }
            if (robot.isAlive()) aliveList.add(robot);
            robot.setActive();
            String a = fieldChanges();
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

    public String fieldChanges() {
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
