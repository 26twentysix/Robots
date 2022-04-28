package com.lilangel.models.actions;

import com.lilangel.models.Field;
import com.lilangel.models.Robot;

public class RepairRobotAction implements RobotAction {

    private final ActionParameters parameters;

    public RepairRobotAction(ActionParameters parameters){
        this.parameters = parameters;
    }

    @Override
    public void handle(Robot robot, Field field) {
        int repairValue = parameters.getGene() / 12;
        robot.reduceEnergy(repairValue / 5.0);
        robot.increaseHP(repairValue);
        robot.getGenome().increaseGenomePointer(parameters.getGene() + 1);
    }
}
