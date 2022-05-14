package com.lilangel.views.game.main;

import com.lilangel.models.enums.ObjectOnTile;

import java.awt.*;

public class Drawer {

    public void draw(Graphics2D g, ObjectOnTile[][] state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                switch (state[i][j]) {
                    case WALL -> drawWall(g, j * 16, i * 16);
                    case EMPTY -> drawEmpty(g, j * 16, i * 16);
                    case ROBOT -> drawRobot(g, j * 16, i * 16);
                    case ENERGY -> drawEnergy(g, j * 16, i * 16);
                    case ROBOT_HP_DEAD -> drawHpDeadRobot(g, j * 16, i * 16);
                    case ROBOT_ENERGY_DEAD -> drawEnergyDeadRobot(g, j * 16, i * 16);
                    case ROBOT_HORIZONTAL_TRAIL -> drawRobotHorizontalTrail(g, j * 16, i * 16);
                    case ROBOT_VERTICAL_TRAIL -> drawRobotVerticalTrail(g, j * 16, i * 16);
                }
            }
        }
    }

    private void drawRobot(Graphics2D g, int x, int y) {
        drawEmpty(g, x, y);
        g.setColor(Color.BLUE);
        g.fillRect(x + 2, y + 2, 13, 13);
    }

    private void drawHpDeadRobot(Graphics2D g, int x, int y) {
        drawEmpty(g, x, y);
        g.setColor(Color.RED);
        g.fillRect(x + 2, y + 2, 13, 13);
    }

    private void drawEnergyDeadRobot(Graphics2D g, int x, int y) {
        drawEmpty(g, x, y);
        g.setColor(Color.CYAN);
        g.fillRect(x + 2, y + 2, 13, 13);
    }

    private void drawRobotHorizontalTrail(Graphics2D g, int x, int y) {
        drawEmpty(g, x, y);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x + 1, y + 6, 15, 6);
    }

    private void drawRobotVerticalTrail(Graphics2D g, int x, int y) {
        drawEmpty(g, x, y);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x + 6, y + 1, 6, 15);
    }

    private void drawEmpty(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 16, 16);
    }

    private void drawWall(Graphics2D g, int x, int y) {
        drawEmpty(g, x, y);
        g.setColor(Color.GRAY);
        g.fillRect(x + 1, y + 1, 15, 15);

    }

    private void drawEnergy(Graphics2D g, int x, int y) {
        drawEmpty(g, x, y);
        g.setColor(Color.PINK);
        g.fillRect(x + 4, y + 4, 8, 8);
    }
}
