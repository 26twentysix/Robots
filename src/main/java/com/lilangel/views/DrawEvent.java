package com.lilangel.views;

import java.awt.event.ActionEvent;

public class DrawEvent extends ActionEvent {
    public DrawEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
