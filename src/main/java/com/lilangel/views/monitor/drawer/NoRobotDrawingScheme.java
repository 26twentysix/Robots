package com.lilangel.views.monitor.drawer;

import com.lilangel.models.robot.Robot;

import java.awt.*;

public class NoRobotDrawingScheme implements DrawingScheme {
    @Override
    public void draw(Robot robot, Graphics2D g2d) {
        g2d.drawString("No robot folks", 5, 20);
    }
}
