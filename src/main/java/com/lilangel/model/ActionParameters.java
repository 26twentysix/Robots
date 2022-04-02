package com.lilangel.model;

class ActionParameters {
    private int deltaY;
    private int deltaX;
    private int stepX;
    private int stepY;

    ActionParameters(int command){
        int direction = command / 10;
        int distance = command % 10 + 1;
        switch (direction) {
            case 1 -> { //move to right
                deltaX = distance;
                stepX = 1;
            }
            case 2 -> {
                deltaY = distance;
                stepY = 1;
            }
            case 3 -> {
                deltaX = -1 * distance;
                stepY = -1;
            }
            case 4 -> {
                deltaY = -1 * distance;
                stepY = -1;
            }
        }
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
