package com.lilangel.views.monitor.drawer;

import com.lilangel.models.robot.Robot;

import java.awt.*;

public class MonitorDrawer {
    DrawingScheme scheme;

    private Robot robot;

    public MonitorDrawer(){
        this.scheme = new NoRobotDrawingScheme();
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public void setScheme(DrawingScheme scheme) {
        this.scheme = scheme;
    }

    public void draw(Graphics2D g2d){
        scheme.draw(robot,g2d);
    }
}
