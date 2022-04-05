package com.lilangel.model.RobotActions;

import com.lilangel.model.Battleground;
import com.lilangel.model.Robot;

/**
 * Robot's attack action, describes how {@link Robot} will attack at the end of the {@link Battleground} tact
 */
public class RobotAttackAction implements RobotAction {
    private final Robot robot;
    private int positionX;
    private int positionY;
    private int cost;

    public RobotAttackAction(Robot robot, int deltaX, int deltaY, int cost) {
        this.robot = robot;
        this.positionX = robot.getPositionX() + deltaX;
        this.positionY = robot.getPositionY() + deltaY;
        this.cost = cost;
    }

    @Override
    public void doAction() {
        robot.attackTile(positionX, positionY);
        robot.changeEnergy(cost);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
