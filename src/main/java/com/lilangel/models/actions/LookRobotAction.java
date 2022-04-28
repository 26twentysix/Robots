package com.lilangel.models.actions;

import com.lilangel.models.Field;
import com.lilangel.models.Robot;

public class LookRobotAction implements RobotAction {

    private final ActionParameters parameters;

    public LookRobotAction(ActionParameters parameters){
        this.parameters = parameters;
    }

    @Override
    public void handle(Robot robot, Field field) {
        robot.getGenome().increaseGenomePointer(field.getTile(robot.getPositionX() + parameters.getDeltaX(),
                                                              robot.getPositionY() + parameters.getDeltaY()));
        robot.reduceEnergy(parameters.getCost()/3.0);
    }
}
