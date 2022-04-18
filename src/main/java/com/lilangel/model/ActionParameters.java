package com.lilangel.model;

import com.lilangel.model.Direction;

public class ActionParameters {
    private int cost;
    private int deltaX;
    private int deltaY;
    private int stepX;
    private int stepY;

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public int getStepX() {
        return stepX;
    }

    public int getStepY() {
        return stepY;
    }

    public int getCost() {
        return cost;
    }

    public ActionParameters(int value){
        Direction direction = getDirection(value);
        int distance = value % 10;
        this.cost = distance;
        switch (direction){
            case RIGHT -> {
                this.deltaX = distance;
                this.stepX = 1;
            }
            case UP -> {
                this.deltaY = -1 * distance;
                this.stepY=-1;
            }
            case LEFT -> {
                this.deltaX = -1 * distance;
                this.stepX = -1;
            }
            case DOWN -> {
                this.deltaY = distance;
                this.stepY = 1;
            }
        }
    }

    private Direction getDirection(int value){
        return switch(value / 10){
            case 0 -> Direction.RIGHT;
            case 1 -> Direction.UP;
            case 2 -> Direction.LEFT;
            case 3 -> Direction.DOWN;
            default -> throw new IllegalStateException("Unexpected value: " + value / 10);
        };
    }

    void shortenDelta(){
        deltaX-=stepX;
        deltaY-=stepY;
    }
}
