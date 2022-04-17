package com.lilangel.gui;

import com.lilangel.model.ObjectOnTile;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Drawer {

    public void draw(Graphics2D g, ObjectOnTile[][] state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                switch (state[i][j]) {
                    case WALL -> drawWall(g,i * 8, j * 8);
                    case EMPTY -> drawEmpty(g,i * 8, j * 8);
                    case ROBOT -> drawRobot(g,i * 8, j * 8);
                    case ENERGY -> drawEnergy(g,i * 8, j * 8);
                }
            }
        }
    }

    private static int round(double value) {
        return (int) (value + 0.5);
    }

    private static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    private static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    /**
     * currently does not work, //TODO исправить
     */
    private void drawRobot(Graphics2D g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillRect(x + 1, y + 1, 6, 6);
    }

    private void drawEmpty(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 8, 8);
    }

    private void drawWall(Graphics2D g, int x, int y) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, 8, 8);

    }

    private void drawEnergy(Graphics2D g, int x, int y) {
        g.setColor(Color.PINK);
        g.fillRect(x + 1, y + 1, 6, 6);
    }
}
