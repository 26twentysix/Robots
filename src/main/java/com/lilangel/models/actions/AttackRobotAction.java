package com.lilangel.models.actions;

import com.lilangel.models.Field;
import com.lilangel.models.ObjectOnTile;
import com.lilangel.models.Robot;

public class AttackRobotAction implements RobotAction {

    private final ActionParameters parameters;

    public AttackRobotAction(ActionParameters parameters){
        this.parameters = parameters;
    }

    @Override
    public void handle(Robot robot, Field field) {
        int deltaX = parameters.getDeltaX() / 2;
        int deltaY = parameters.getDeltaY() / 2;
        int targetX = robot.getPositionX() + deltaX;
        int targetY = robot.getPositionY() + deltaY;
        ObjectOnTile target = field.getTile(targetX, targetY);

        if (target == ObjectOnTile.ROBOT && targetX != robot.getPositionX() && targetY != robot.getPositionY()) {
            Robot enemy = field.getRobot(targetX, targetY);
            enemy.reduceHP(parameters.getCost());
        }

        if (target == ObjectOnTile.WALL && parameters.getCost() > 6)
            field.setTile(targetX, targetY, ObjectOnTile.EMPTY);
        if (target == ObjectOnTile.ENERGY && parameters.getCost() > 4)
            field.setTile(targetX, targetY, ObjectOnTile.EMPTY);

        robot.getGenome().increaseGenomePointer(target);
        robot.reduceEnergy(parameters.getCost());
    }
}
