package com.lilangel.views.monitor.drawer;

import com.lilangel.models.robot.Robot;

import java.awt.*;

public interface DrawingScheme {
    void draw(Robot robot, Graphics2D g2d);
}
