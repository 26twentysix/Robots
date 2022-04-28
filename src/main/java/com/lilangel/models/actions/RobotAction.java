package com.lilangel.models.actions;

import com.lilangel.models.Field;
import com.lilangel.models.Robot;

public interface RobotAction {
    void handle(Robot robot, Field field);
}
