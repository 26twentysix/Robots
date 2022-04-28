package com.lilangel.models.actions;

import com.lilangel.models.Field;
import com.lilangel.models.ObjectOnTile;
import com.lilangel.models.Robot;

public class MoveRobotAction implements RobotAction {

    private final ActionParameters parameters;

    public MoveRobotAction(ActionParameters parameters){
        this.parameters = parameters;
    }

    @Override
    public void handle(Robot robot, Field field) {
        int maxDeltaX = parameters.getDeltaX() / 3;
        int maxDeltaY = parameters.getDeltaY() / 3;
        int deltaX = parameters.getStepX();
        int deltaY = parameters.getStepY();
        ObjectOnTile target = field.getTile(robot.getPositionX() + deltaX, robot.getPositionY() + deltaY);
        ObjectOnTile previousTarget = ObjectOnTile.EMPTY;
        while ((target == ObjectOnTile.EMPTY || target == ObjectOnTile.ENERGY) && (Math.abs(deltaX) < Math.abs(maxDeltaX) || Math.abs(deltaY) < Math.abs(maxDeltaY))) {
            deltaX += parameters.getStepX();
            deltaY += parameters.getStepY();
            previousTarget = target;
            target = field.getTile(robot.getPositionX() + deltaX, robot.getPositionY() + deltaY);
        }

        if(target == ObjectOnTile.WALL || target == ObjectOnTile.ROBOT){
            robot.reduceHP(5);
            target = previousTarget;
            deltaX -= parameters.getStepX();
            deltaY -= parameters.getStepY();
        }

        field.setTile(robot.getPositionX(), robot.getPositionY(), ObjectOnTile.EMPTY);
        robot.changePosition(deltaX, deltaY);
        field.setTile(robot.getPositionX(), robot.getPositionY(), ObjectOnTile.ROBOT);

        if (target == ObjectOnTile.ENERGY)
            robot.pickUpEnergyCell();

        robot.reduceEnergy(parameters.getCost());
        robot.getGenome().increaseGenomePointer(target);
    }
}
