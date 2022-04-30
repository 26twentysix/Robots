package com.lilangel.models;

import com.lilangel.models.robot.Robot;

public interface MonitorModel extends Model {
    Robot getObservableRobot();
}
