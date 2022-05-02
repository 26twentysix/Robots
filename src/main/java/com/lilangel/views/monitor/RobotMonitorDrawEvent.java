package com.lilangel.views.monitor;

import com.lilangel.models.robot.Robot;
import com.lilangel.views.DrawEvent;

import java.awt.event.ActionEvent;

public class RobotMonitorDrawEvent extends DrawEvent {

    final Robot robot;

    public RobotMonitorDrawEvent(Object source, int id, String command, Robot robot) {
        super(source, id, command);
        this.robot = robot;
    }

    public RobotMonitorDrawEvent(ActionEvent e, Robot robot) {
        super(e.getSource(), e.getID(), e.getActionCommand());
        this.robot = robot;
    }
}
