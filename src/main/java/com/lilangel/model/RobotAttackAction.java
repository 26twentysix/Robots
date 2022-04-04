package com.lilangel.model;

/**
 * Robot's attack action, describes how {@link Robot} will attack at the end of the {@link Battleground} tact
 */
public class RobotAttackAction implements RobotAction {
    private final Robot robot;
    private int positionX;
    private int positionY;
    private int cost;

    RobotAttackAction(Robot robot, int positionX, int positionY, int cost) {
        this.robot = robot;
        this.positionX = positionX;
        this.positionY = positionY;
        this.cost = cost;
    }

    @Override
    public void doAction() {
        robot.attackTile(positionX,positionY);
        robot.reduceEnergy(cost);
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
