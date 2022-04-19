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
                    case WALL -> drawWall(g,j * 16, i * 16);
                    case EMPTY -> drawEmpty(g,j * 16, i * 16);
                    case ROBOT -> drawRobot(g,j * 16, i * 16);
                    case ENERGY -> drawEnergy(g,j * 16, i * 16);
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

    private void drawRobot(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 16, 16);
        g.setColor(Color.BLUE);
        g.fillRect(x + 2, y + 2, 13, 13);
    }

    private void drawEmpty(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 16, 16);
    }

    private void drawWall(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 16, 16);
        g.setColor(Color.GRAY);
        g.fillRect(x+1, y+1, 15, 15);

    }

    private void drawEnergy(Graphics2D g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 16, 16);
        g.setColor(Color.PINK);
        g.fillRect(x + 4, y + 4, 8, 8);
    }
}
