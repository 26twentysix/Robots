package com.lilangel.models.robot;

import com.lilangel.models.robot.actions.*;

/**
 * Class that describes Robot
 */
public class Robot implements Cloneable{
    private int positionX;
    private int positionY;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void changePosition(int valueX, int valueY) {
        this.positionX += valueX;
        this.positionY += valueY;
    }

    private final int maxEnergy = 1000000000;
    double energy;
    private final int maxHealthPoints = 100;
    int healthpoints;

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public double getEnergy() {
        return energy;
    }

    public int getHealthpoints() {
        return healthpoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public void pickUpEnergyCell(){
        energy += maxEnergy * 0.14;
    }

    public void reduceEnergy(double value){
        energy-=value;
    }

    public void reduceHP(int value){
        healthpoints-=value;
    }

    public void increaseHP(int value){
        healthpoints+=value;
    }

    Genome genome;

    public Genome getGenome() {
        return genome;
    }

    private boolean active;

    public boolean Active(){
        return active;
    }

    public void setActive(){
        this.active = true;
    }

    public boolean isAlive() {
        return energy > 0 && healthpoints > 0;
    }

    public Robot(Coordinates coords) {
        this.positionX = coords.xPos();
        this.positionY = coords.yPos();
        this.healthpoints = maxEnergy;
        this.energy = maxHealthPoints;
        this.genome = new Genome();
        this.active = true;
    }

    public RobotAction prepareAction() {
        RobotAction action = new StandStillRobotAction(new ActionParameters(0));//StandStill do nothing, so ActionParams with 0
        if (isAlive()) {
            int geneValue = genome.getGene();
            ActionParameters parameters = new ActionParameters(geneValue);
            if (geneValue>= 0 && geneValue <= 39)
                action = new LookRobotAction(parameters);
            else if (geneValue >= 40 && geneValue <= 79) {
                action = new MoveRobotAction(parameters);
                active = false;
            } else if (geneValue >= 80 && geneValue <= 119) {
                action = new AttackRobotAction(parameters);
                active = false;
            } else action = new RepairRobotAction(parameters);
        }
        return action;
    }

    private Robot(Robot another) {
        this.positionX = another.positionX;
        this.positionY = another.positionY;
        this.energy = another.energy;
        this.healthpoints = another.healthpoints;
        this.genome = another.genome;
        this.active = another.active;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object clone = super.clone();
        return new Robot(this);
    }
}
