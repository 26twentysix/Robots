package com.lilangel.model;

/**
 * Robot's move action, describes how {@link Robot} will move at the end of the {@link Battleground} tact
 */
public class RobotMoveAction implements RobotAction {
    private Robot robot;
    private int positionX;
    private int positionY;

    RobotMoveAction(Robot robot, int positionX, int positionY){
        this.robot = robot;
        this.positionX=positionX;
        this.positionY=positionY;
    }

    @Override
    public void doAction() {

    }
}
