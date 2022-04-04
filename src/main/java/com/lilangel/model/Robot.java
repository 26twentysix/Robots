package com.lilangel.model;

import java.util.Random;

/**
 * Class that describes Robot
 */
public class Robot {
    /**
     * Battleground on which the robot is located
     */
    private Battleground battleground;
    private final int fieldWidth;
    private final int fieldHeight;
    /**
     * a flag that signals whether the robot has chosen the next action
     */
    private boolean ready;

    /**
     * Getter for {@link Robot#ready}
     *
     * @return {@link Robot#ready}
     */
    public boolean isReady() {
        return this.ready;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    /**
     * Coordinates of the robot
     */
    private int positionX;
    private int positionY;

    /**
     * Array of integers that presents a genome
     */
    private int[] genome = new int[255];
    /**
     * Number that points to current gen in genome
     */
    private int instructionPointer;

    void reduceEnergy(int energy) {
        if (energy < 0)
            return;
        this.energy -= energy;
    }

    /**
     * Number of robots energy, it consumes by all robot actions, when 0 - robot can't move
     */
    private int energy;
    /**
     * Robots health points, when 0 - robot's body will no longer exist
     */
    private int healthPoints;

    /**
     * Action that robot will preform at the end of Battleground tact
     */
    private RobotAction preparedAction;

    public Robot(Battleground battleground) {
        this.battleground = battleground;
        fieldWidth = battleground.field.length;
        fieldHeight = battleground.field[0].length;
        Random rnd = new Random();
        this.genome = rnd.ints(255, 0, 256).toArray();
        energy = 100;
        healthPoints = 30;
        instructionPointer = rnd.nextInt(255);
        ready = false;
    }

    /**
     * Method that prepares robot's action, basing on genome
     */
    public void prepareAction() {
        int count = 0;
        preparedAction = new RobotStandStillAction();
        if (energy > 0 && healthPoints > 0)
            while (!ready && count < 15) {
                int currentCommand = genome[instructionPointer];
                if (currentCommand >= 10 && currentCommand <= 49)
                    look(currentCommand);
                else if (currentCommand >= 50 && currentCommand <= 89) {
                    preparedAction = move(currentCommand);
                    ready = true;
                } else if (currentCommand >= 90 && currentCommand <= 129) {
                    preparedAction = attack(currentCommand);
                    ready = true;
                } else repair(currentCommand);
                count++;
            }
        if (healthPoints < 1)
            battleground.killRobot(positionX, positionY);
    }

    /**
     * Method that performs robot's action
     */
    public void doPreparedAction() {
        preparedAction.doAction();
    }

    /**
     * Method that changes instruction pointer according to object, that was the target of robot action
     *
     * @param objectType - int value of some {@link ObjectOnTile} instance
     */
    private void changeInstructionPointer(int objectType) {
        int offset = switch (objectType) {//енамы тут не работают, потому что после case может идти только константа, а енам с точки зрения компилятора не константа
            case 0 -> 40; //пустое место - оффсет 40
            case 1 -> 20; //энергия - оффсет 20
            case 2 -> 30; //робот - оффест 30
            case 3 -> 10; //стена - оффсет 10
            default -> objectType;
        };
        instructionPointer = (instructionPointer + offset) % 255;
    }

    /**
     * Method that moves the bot
     *
     * @param command number from genome, that is corresponds to moving action
     * @return instance of {@link RobotMoveAction}, that describes certain move
     */
    private RobotAction move(int command) {
        command -= 40; //чтобы правильно работало, все по задумке

        ActionParameters params = new ActionParameters(command);
        int deltaX = params.getDeltaX(), deltaY = params.getDeltaY(), stepX = params.getStepX(), stepY = params.getStepY();
        int obj = selectTileOnField(positionX + deltaX, positionY + deltaY).ordinal();
        int previousObj = 0;
        while (obj == ObjectOnTile.ROBOT.ordinal() || obj == ObjectOnTile.WALL.ordinal()) {
            deltaX -= stepX;
            deltaY -= stepY;
            previousObj = obj;
            obj = selectTileOnField(positionX + deltaX, positionY + deltaY).ordinal();
        }
        changeInstructionPointer(obj);
        return new RobotMoveAction(this, params.getDirection(),
                positionX + deltaX, positionY + deltaY, Math.max(Math.abs(deltaX), Math.abs(deltaY)));
    }


    /**
     * Method that makes robot to attack
     *
     * @param command number from genome, that corresponds to attack action
     * @return instance of {@link RobotAttackAction}, that describes certain attack
     */
    private RobotAction attack(int command) {
        command -= 80; //тоже чтобы правильно работало, читай genome.txt
        ActionParameters params = new ActionParameters(command);
        int deltaX = params.getDeltaX(), deltaY = params.getDeltaY();
        int obj = selectTileOnField(positionX + deltaX, positionY + deltaY).ordinal();
        changeInstructionPointer(obj);
        return new RobotAttackAction(this, positionX + params.getStepX(), positionY + params.getStepY(),
                Math.max(deltaX, deltaY));
    }

    /**
     * Method that makes robot to look around
     *
     * @param command number from genome that corresponds to looking action
     */
    private void look(int command) {
        ActionParameters params = new ActionParameters(command);
        int deltaX = params.getDeltaX(), deltaY = params.getDeltaY(), stepX = params.getStepX(), stepY = params.getStepY();
        int obj = selectTileOnField(positionX + deltaX, positionY + deltaY).ordinal();
        while (obj == ObjectOnTile.ROBOT.ordinal() || obj == ObjectOnTile.WALL.ordinal()) {
            deltaX += stepX;
            deltaY += stepY;
            obj = selectTileOnField(positionX + deltaX, positionY + deltaY).ordinal();
        }
        energy -= Math.max(deltaX, deltaY);
        changeInstructionPointer(obj);
    }

    /**
     * Method that makes robot to repair him self
     *
     * @param command - number from genome that corresponds to repairing action
     */
    private void repair(int command) {
        int delta = (command - 130) % 20;
        energy -= delta;
        healthPoints += delta;
        if (energy < 0) energy = 0;
        if (healthPoints > 70) healthPoints = 70;
        changeInstructionPointer(command);
    }

    protected void changePosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    ObjectOnTile selectTileOnField(int x, int y) {
        if (x >= fieldWidth)
            x -= fieldWidth;
        if (x < 0)
            x += fieldWidth;
        if (y >= fieldHeight)
            y -= fieldHeight;
        if (y < 0)
            y += fieldHeight;
        return battleground.field[x][y];
    }

    void attackTile(int x, int y) {
        ObjectOnTile obj = selectTileOnField(x, y);
        if (obj == ObjectOnTile.ROBOT) {
            battleground.killRobot(x, y);
        }
        battleground.setTileEmpty(x, y);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < 6; i++) {
            hash += genome[i];
            hash *= 17;
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else {
            Robot another = (Robot) obj;
            return this.positionX == another.positionX && this.positionY == another.positionY;
        }
    }
}
