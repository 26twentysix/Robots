package com.lilangel.views.monitor;

import java.awt.event.ActionEvent;

public class SelectedRobotUpdateEvent extends ActionEvent {

    public SelectedRobotUpdateEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
