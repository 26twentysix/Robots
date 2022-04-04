package com.lilangel.model;

class ActionParameters {
    private int deltaY;
    private int deltaX;
    private int stepX;
    private int stepY;

    private Direction direction;

    ActionParameters(int command) {
        int dir = command / 10;
        int distance = command % 10 + 1;
        switch (dir) {
            case 1 -> { //move to right
                this.direction = Direction.RIGHT;
                deltaX = distance;
                stepX = 1;
            }
            case 2 -> {
                this.direction = Direction.DOWN;
                deltaY = distance;
                stepY = 1;
            }
            case 3 -> {
                this.direction = Direction.LEFT;
                deltaX = -1 * distance;
                stepY = -1;
            }
            case 4 -> {
                this.direction = Direction.UP;
                deltaY = -1 * distance;
                stepY = -1;
            }
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getStepX() {
        return stepX;
    }

    public int getStepY() {
        return stepY;
    }
}
