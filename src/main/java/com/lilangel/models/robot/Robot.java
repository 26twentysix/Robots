package com.lilangel.models.robot;

import com.lilangel.models.enums.ObjectOnTile;
import com.lilangel.models.robot.actions.*;

import java.util.HashSet;

/**
 * Class that describes Robot
 */
public class Robot implements Cloneable {
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

    private HashSet<Coordinates> trail;

    public void addTileToTrail(Coordinates coordinates) {
        trail.add(coordinates);
    }

    public HashSet<Coordinates> getTrail() {
        return trail;
    }

    private final int maxEnergy = 100;
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

    public void pickUpEnergyCell() {
        energy += maxEnergy * 0.14;
    }

    public void reduceEnergy(double value) {
        energy -= value;
    }

    public void reduceHP(int value) {
        healthpoints -= value;
    }

    public void increaseHP(int value) {
        healthpoints = Math.min(maxHealthPoints, healthpoints + value);
    }

    Genome genome;

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    private boolean active;

    public boolean Active() {
        return active;
    }

    public void setActive(boolean value) {
        this.active = value;
    }

    public boolean isAlive() {
        return energy > 0 && healthpoints > 0;
    }

    public ObjectOnTile getState() {
        if (healthpoints <= 0)
            return ObjectOnTile.ROBOT_HP_DEAD;
        else if (((int) energy) <= 0) return ObjectOnTile.ROBOT_ENERGY_DEAD;
        return ObjectOnTile.ROBOT;
    }

    public Robot(Coordinates coords) {
        this.positionX = coords.xPos();
        this.positionY = coords.yPos();
        this.healthpoints = maxEnergy;
        this.energy = maxHealthPoints;
        this.genome = new Genome();
        this.active = true;
        this.trail = new HashSet<>();
    }

    public RobotAction prepareAction() {
        RobotAction action = new StandStillRobotAction(new ActionParameters(0));//StandStill do nothing, so ActionParams with 0
        if (isAlive()) {
            int geneValue = genome.getGene();
            ActionParameters parameters = new ActionParameters(geneValue);
            if (geneValue >= 0 && geneValue <= 39)
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

    @Override
    public Robot clone() throws CloneNotSupportedException {
        Robot clone = (Robot) super.clone();
        clone.genome = this.genome.clone();
        return clone;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d), E=%.2f, H=%d", this.positionX, this.positionY, this.energy, this.healthpoints);
    }
}
