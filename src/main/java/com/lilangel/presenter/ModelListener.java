package com.lilangel.presenter;

import jdk.jshell.spi.ExecutionControl;

import java.awt.event.ActionEvent;

public interface ModelListener {
    void onModelUpdateEvent(ActionEvent e) throws ExecutionControl.NotImplementedException;
}
