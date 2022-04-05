package com.lilangel.model.RobotActions;

import com.lilangel.model.Battleground;
import com.lilangel.model.Direction;
import com.lilangel.model.ObjectOnTile;
import com.lilangel.model.Robot;

/**
 * Robot's move action, describes how {@link Robot} will move at the end of the {@link Battleground} tact
 */
public class RobotMoveAction implements RobotAction {
    private Robot robot;
    private Direction direction;
    private int cost;

    private final int fieldWidth = 300;//очень плохо что тут константы
    private final int fieldHeight = 200;//если будем менять размер поля, тут придется менять вручную

    private int previousPositionX;
    private int previousPositionY;
    private int targetPositionX;
    private int targetPositionY;

    public RobotMoveAction(Robot robot, Direction direction, int targetPositionX, int targerPositionY, int cost) {
        this.robot = robot;
        this.direction = direction;
        this.previousPositionX = robot.getPositionX();
        this.previousPositionY = robot.getPositionY();
        this.targetPositionX = targetPositionX % this.fieldWidth;
        this.targetPositionY = targerPositionY % this.fieldHeight;
        this.cost = cost;
    }

    @Override
    public void doAction() {
        if (targetPositionX > fieldWidth)
            targetPositionX -= fieldWidth;
        if (targetPositionX < 0)
            targetPositionX += fieldWidth;
        if (targetPositionY > fieldHeight)
            targetPositionY -= fieldHeight;
        if (targetPositionY < 0)
            targetPositionY += fieldHeight;
        ObjectOnTile obj = robot.selectTileOnField(targetPositionX, targetPositionY);
        while (obj == ObjectOnTile.WALL || obj == ObjectOnTile.ROBOT) {
            shortenStep();
            obj = robot.selectTileOnField(targetPositionX, targetPositionY);
        }
        if(obj == ObjectOnTile.ENERGY)
            robot.changeEnergy(20);
        robot.changePosition(targetPositionX, targetPositionY);
        robot.changeEnergy(-cost);
    }

    public int getPreviousPositionX() {
        return previousPositionX;
    }

    public int getPreviousPositionY() {
        return previousPositionY;
    }

    public int getTargetPositionX() {
        return targetPositionX;
    }

    public int getTargetPositionY() {
        return targetPositionY;
    }

    void shortenStep() {
        switch (direction) {
            case LEFT -> {
                targetPositionX++;
                if (targetPositionX > fieldWidth)
                    targetPositionX -= fieldHeight;
            }
            case DOWN -> {
                targetPositionY++;
                if (targetPositionY > fieldHeight)
                    targetPositionY -= fieldHeight;
            }
            case RIGHT -> {
                targetPositionX--;
                if (targetPositionX < 0)
                    targetPositionX += fieldWidth;
            }
            case UP -> {
                targetPositionY--;
                if (targetPositionY < 0)
                    targetPositionY += fieldHeight;
            }
        }
    }
}
