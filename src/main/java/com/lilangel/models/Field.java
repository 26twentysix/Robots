package com.lilangel.models;

import com.lilangel.models.robot.actions.RobotAction;
import com.lilangel.models.enums.ModelConstants;
import com.lilangel.models.enums.ObjectOnTile;
import com.lilangel.models.robot.Coordinates;
import com.lilangel.models.robot.Robot;

import java.nio.charset.CoderResult;
import java.util.*;

public class Field {
    private ObjectOnTile[][] field;

    private final int width = ModelConstants.FIELD_WIDTH.value;
    private final int height = ModelConstants.FIELD_HEIGT.value;

    private Map<Coordinates, Robot> robotsMapping;
    private ArrayList<Robot> robots;
    private int aliveCount;
    private final int robotsLimit = ModelConstants.ROBOTS_COUNT.value;

    private Robot observableRobot;

    int generationCount = 0;
    int iterationsCount = 0;

    public ArrayList<Robot> getAliveList() {
        return this.robots;
    }

    public int getAliveCount() {
        return aliveCount;
    }

    void observeRobot(int x, int y) {
        if (robotsMapping.containsKey(new Coordinates(x, y))) {
            this.observableRobot = robotsMapping.get(new Coordinates(x, y));
        }
    }

    public Robot getObservableRobot() {
        try {
            if (observableRobot != null)
                return observableRobot.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObjectOnTile getTile(int x, int y) {
        Coordinates normalized = normalize(new Coordinates(x, y));

        return field[normalized.yPos()][normalized.xPos()];
    }

    public void setTile(Coordinates coordinates, ObjectOnTile obj) {
        Coordinates normalized = normalize(coordinates);

        this.field[normalized.yPos()][normalized.xPos()] = obj;
    }

    private Coordinates normalize(Coordinates coordinates) {
        int x = coordinates.xPos();
        int y = coordinates.yPos();
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


    public Field() {
        this.field = new ObjectOnTile[height][width];
        this.robots = new ArrayList<>();
        this.robotsMapping = new HashMap<>();

        summonRobots();
        fillField();
        this.aliveCount = robots.size();
    }

    public void prepareNewGeneration(ArrayList<Robot> aliveRobots) {
        this.field = new ObjectOnTile[height][width];
        this.robots = new ArrayList<>();
        this.robotsMapping = new HashMap<>();

        createOffspring(aliveRobots);

        fillField();
        this.aliveCount = robots.size();

        generationCount++;
    }

    private void createOffspring(ArrayList<Robot> aliveRobots) {
        if (aliveRobots.size() == 0) {
            summonRobots();
            return;
        }

        if (aliveRobots.size() < 10) {
            while (aliveRobots.size() < 10) {
                Robot forefather = new Robot(getEmptyCellCoordinates());
                forefather.setGenome(aliveRobots.get(aliveRobots.size() - 1).getGenome());
                forefather.getGenome().mutateGenome();
                aliveRobots.add(forefather);
            }
        }

        for (Robot robot : aliveRobots) {
            for (int i = 0; i < 9; i++) {
                Robot descendant = new Robot(getEmptyCellCoordinates());
                descendant.setGenome(robot.getGenome());
                robots.add(descendant);
                field[descendant.getPositionY()][descendant.getPositionX()] = ObjectOnTile.ROBOT;
                robotsMapping.put(new Coordinates(descendant.getPositionX(), descendant.getPositionY()), descendant);
            }
            Robot mutant = new Robot(getEmptyCellCoordinates());
            mutant.setGenome(robot.getGenome());
            mutant.getGenome().mutateGenome();
        }
    }

    private void summonRobots() {
        for (int i = 0; i < robotsLimit; i++) {
            Robot robot = new Robot(getEmptyCellCoordinates());
            robots.add(robot);
            field[robot.getPositionY()][robot.getPositionX()] = ObjectOnTile.ROBOT;
            robotsMapping.put(new Coordinates(robot.getPositionX(), robot.getPositionY()), robot);
        }
    }

    private Coordinates getEmptyCellCoordinates() {
        Random random = new Random();
        int xPos = random.nextInt(ModelConstants.FIELD_WIDTH.value);
        int yPos = random.nextInt(ModelConstants.FIELD_HEIGT.value);
        ObjectOnTile obj = field[yPos][xPos];
        while (obj != ObjectOnTile.EMPTY && obj != null) {
            xPos = random.nextInt(ModelConstants.FIELD_WIDTH.value);
            yPos = random.nextInt(ModelConstants.FIELD_HEIGT.value);
            obj = field[yPos][xPos];
        }
        return new Coordinates(xPos, yPos);
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
        ArrayList<Robot> aliveList = new ArrayList<>();
        for (Robot robot : robots) {
            for (Coordinates tile : robot.getTrail()) {
                Coordinates normalized = normalize(tile);
                field[normalized.yPos()][normalized.xPos()] = ObjectOnTile.EMPTY;
            }
            robot.getTrail().clear();
            robotsMapping.remove(new Coordinates(robot.getPositionX(), robot.getPositionY()));
            int iterations = 0;
            List<RobotAction> list = new ArrayList<>();
            while (robot.Active()) {
                RobotAction action = robot.prepareAction();
                list.add(action);
                action.handle(robot, this);
                iterations++;
                if (iterations > 9) {
                    break;
                }
            }

            ObjectOnTile robotState = robot.getState();
            if (robotState == ObjectOnTile.ROBOT) {
                aliveList.add(robot);
                robot.setActive(true);
            } else {
                Coordinates robotCoordinates = normalize(new Coordinates(robot.getPositionX(), robot.getPositionY()));
                field[robotCoordinates.yPos()][robotCoordinates.xPos()] = robotState;
            }

            robotsMapping.put(normalize(new Coordinates(robot.getPositionX(), robot.getPositionY())), robot);
        }

        if (iterationsCount % 5 == 0)
            generateNewEnergy();

        this.robots = aliveList;
        this.aliveCount = aliveList.size();
        iterationsCount++;
    }

    private void generateNewEnergy() {
        for (int i = 0; i < 20; i++) {
            Coordinates emptyCellCoordinates = getEmptyCellCoordinates();
            field[emptyCellCoordinates.yPos()][emptyCellCoordinates.xPos()] = ObjectOnTile.ENERGY;
        }
    }

    public ObjectOnTile[][] makeCopyOfCurrentState() {
        var result = new ObjectOnTile[height][width];
        for (int i = 0; i < height; i++)
            System.arraycopy(field[i], 0, result[i], 0, width);
        return result;
    }
}
