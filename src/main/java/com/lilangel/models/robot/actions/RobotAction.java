package com.lilangel.models.robot.actions;

import com.lilangel.models.Field;
import com.lilangel.models.robot.Robot;

public interface RobotAction {
    void handle(Robot robot, Field field);
}
