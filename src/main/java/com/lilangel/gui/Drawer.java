package com.lilangel.gui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class Drawer {

    public void draw(Graphics2D g) {
        Random rnd = new Random();
        int x = rnd.nextInt(1, 150);
        int y = rnd.nextInt(1, 150);
        drawOval(g, x, y, 20, 40);
        fillOval(g, x, y, 20, 40);
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
    private void drawRobot(Graphics2D g, int x, int y, double direction) {
        int robotCenterX = round(x);
        int robotCenterY = round(y);
        AffineTransform t = AffineTransform.getRotateInstance(direction, robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX + 10, robotCenterY, 5, 5);
    }
}
