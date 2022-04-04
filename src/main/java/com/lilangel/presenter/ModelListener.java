package com.lilangel.presenter;

import jdk.jshell.spi.ExecutionControl;

import java.awt.event.ActionEvent;

/**
 * Interface that declares what events Model can invoke and Presenter must handle
 */
public interface ModelListener {
    void onModelUpdateEvent(ActionEvent e) throws ExecutionControl.NotImplementedException;
}