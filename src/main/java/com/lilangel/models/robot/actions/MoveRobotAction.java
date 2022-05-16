package com.lilangel.models.robot.actions;

import com.lilangel.models.Field;
import com.lilangel.models.enums.ObjectOnTile;
import com.lilangel.models.robot.Coordinates;
import com.lilangel.models.robot.Robot;

import java.util.ArrayList;
import java.util.List;

public class MoveRobotAction implements RobotAction {

    private final ActionParameters parameters;

    public MoveRobotAction(ActionParameters parameters){
        this.parameters = parameters;
    }

    @Override
    public void handle(Robot robot, Field field) {
        int maxDeltaX = (int)Math.ceil(parameters.getDeltaX() / 3.0);
        int maxDeltaY = (int)Math.ceil(parameters.getDeltaY() / 3.0);
        int deltaX = parameters.getStepX();
        int deltaY = parameters.getStepY();
        ObjectOnTile target = field.getTile(robot.getPositionX() + deltaX, robot.getPositionY() + deltaY);
        ObjectOnTile previousTarget = ObjectOnTile.EMPTY;
        List<Coordinates> trail = new ArrayList<>();

        trail.add(new Coordinates(robot.getPositionX(), robot.getPositionY()));
        while ((target == ObjectOnTile.EMPTY || target == ObjectOnTile.ENERGY) && (Math.abs(deltaX) < Math.abs(maxDeltaX) || Math.abs(deltaY) < Math.abs(maxDeltaY))) {
            trail.add(new Coordinates(robot.getPositionX()+deltaX, robot.getPositionY()+deltaY));
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

        if(deltaX == 0 && deltaY == 0) {
            robot.getTrail().clear();
            robot.reduceEnergy(1);
            return;
        }

        field.setTile(new Coordinates(robot.getPositionX(), robot.getPositionY()), ObjectOnTile.EMPTY);
        robot.changePosition(deltaX, deltaY);
        field.setTile(new Coordinates(robot.getPositionX(), robot.getPositionY()), ObjectOnTile.ROBOT);

        ObjectOnTile trackTile = maxDeltaX == 0 ? ObjectOnTile.ROBOT_VERTICAL_TRAIL : ObjectOnTile.ROBOT_HORIZONTAL_TRAIL;

        for(Coordinates tile : trail){
            field.setTile(new Coordinates(tile.xPos(),tile.yPos()),
                    trackTile);
            robot.addTileToTrail(tile);
        }

        if (target == ObjectOnTile.ENERGY)
            robot.pickUpEnergyCell();

        robot.reduceEnergy(parameters.getCost());
        robot.getGenome().increaseGenomePointer(target);
    }
}
