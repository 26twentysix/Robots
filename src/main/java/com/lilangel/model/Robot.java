package com.lilangel.model;

import java.util.Random;

/**
 * Class that describes Robot
 */
public class Robot {
    private int positionX;
    private int positionY;
    Field field;

    private final int maxEnergy = 100;
    double energy;
    private final int maxHealthPoints = 100;
    int healthpoints;

    final int genomeLimit = 240;
    int[] genome = new int[genomeLimit];
    int genomePointer;
    final int basePointerIncrease = 10;


    public void increaseGenomePointer(ObjectOnTile obj) {
        int value = switch (obj) {
            case WALL -> basePointerIncrease;
            case ROBOT -> basePointerIncrease * 3;
            case ENERGY -> basePointerIncrease * 2;
            case EMPTY -> basePointerIncrease * 4;
        };

        this.genomePointer += value;
        this.genomePointer %= genomeLimit;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    boolean isAlive() {
        return energy > 0 && healthpoints > 0;
    }

    public Robot(Field field) {
        this.field = field;
        Random random = new Random();
        int xPos = random.nextInt(ModelConstants.FIELD_WIDTH.value);
        int yPos = random.nextInt(ModelConstants.FIELD_HEIGT.value);
        ObjectOnTile obj = field.getTile(xPos, yPos);
        while(obj != ObjectOnTile.EMPTY && obj != null){
            xPos = random.nextInt(ModelConstants.FIELD_WIDTH.value);
            yPos = random.nextInt(ModelConstants.FIELD_HEIGT.value);
            obj = field.getTile(xPos, yPos);
        }
        this.positionX = xPos;
        this.positionY = yPos;

        for (int i = 0; i < genomeLimit; i++) {
            genome[i] = random.nextInt(genomeLimit);
        }

        this.genomePointer = random.nextInt(genomeLimit);

        this.healthpoints = maxEnergy;
        this.energy = maxHealthPoints;
    }

    void performAction() {
        boolean ready = false;
        int iterations = 0;
        while (!ready && isAlive()) {
            if (genome[genomePointer] >= 0 && genome[genomePointer] <= 39)
                look(genome[genomePointer]);
            else if (genome[genomePointer] >= 40 && genome[genomePointer] <= 79) {
                move(genome[genomePointer] % 40);
                ready = true;
            } else if (genome[genomePointer] >= 80 && genome[genomePointer] <= 119) {
                attack(genome[genomePointer] % 80);
                ready = true;
            } else repair(genome[genomePointer] % 120);
            iterations++;
            if (iterations > 9)
                ready = true;
        }
    }

    private void look(int value) {
        ActionParameters parameters = new ActionParameters(value);
        increaseGenomePointer(field.getTile(this.positionX + parameters.getDeltaX(), this.positionY + parameters.getDeltaY()));
        energy -= parameters.getCost() / 3;
    }

    private void move(int value) {
        ActionParameters parameters = new ActionParameters(value);
        int deltaX = parameters.getDeltaX() / 3;
        int deltaY = parameters.getDeltaY() / 3;
        ObjectOnTile target = field.getTile(this.positionX + deltaX, this.positionY + deltaY);
        ObjectOnTile previousTarget = ObjectOnTile.EMPTY;
        while (target != ObjectOnTile.EMPTY && target != ObjectOnTile.ENERGY && (deltaX > 0 || deltaY > 0)) {
            deltaX-=parameters.getStepX();
            deltaY-=parameters.getStepY();
            previousTarget = target;
            target = field.getTile(this.positionX + deltaX, this.positionY + deltaY);
        }

        field.setTile(this.positionX, this.positionY, ObjectOnTile.EMPTY);
        this.positionX += parameters.getDeltaX();
        this.positionY += parameters.getDeltaY();
        field.setTile(this.positionX, this.positionY, ObjectOnTile.ROBOT);

        if (previousTarget == ObjectOnTile.WALL) {
            healthpoints -= 5;
            increaseGenomePointer(previousTarget);
        } else increaseGenomePointer(target);

        if (target == ObjectOnTile.ENERGY)
            energy += maxEnergy * 0.14;

        this.energy -= parameters.getCost();
    }

    private void attack(int value) {
        ActionParameters parameters = new ActionParameters(value);
        int deltaX = parameters.getDeltaX() / 2;
        int deltaY = parameters.getDeltaY() / 2;
        int targetX = this.positionX + deltaX;
        int targetY = this.positionY + deltaY;
        ObjectOnTile target = field.getTile(targetX, targetY);

        if (target == ObjectOnTile.ROBOT && targetX != this.positionX && targetY != this.positionY) {
            Robot enemy = field.getRobot(targetX, targetY);
            enemy.hitRobot(parameters.getCost());
        }

        if (target == ObjectOnTile.WALL && parameters.getCost() > 6)
            field.setTile(targetX, targetY, ObjectOnTile.EMPTY);
        if (target == ObjectOnTile.ENERGY && parameters.getCost() > 4)
            field.setTile(targetX, targetY, ObjectOnTile.EMPTY);

        increaseGenomePointer(target);
        energy-=parameters.getCost();
    }

    private void repair(int value) {
        int repairValue = value / 12;
        energy -= repairValue/7.0;
        genomePointer += value+1;
        genomePointer%=genomeLimit;
    }

    private void hitRobot(int damage) {
        healthpoints -= damage;
    }
}
