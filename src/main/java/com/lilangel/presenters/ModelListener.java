package com.lilangel.presenters;

import java.awt.event.ActionEvent;

/**
 * Interface that declares what events Model can invoke and Presenter must handle
 */
public interface ModelListener {
    void onModelUpdateEvent(ActionEvent e);
}
