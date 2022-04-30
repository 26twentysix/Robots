package com.lilangel.presenters;

import java.awt.event.ActionEvent;

/**
 * Intefrace that declare what events View can invoke and Presenter must handle
 */
public interface ViewListener {
    void onViewEvent(ActionEvent e);
}
