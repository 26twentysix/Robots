package com.lilangel.presenter;

import java.awt.event.ActionEvent;

/**
 * Intefrace that declare what events View can invoke and Presenter must handle
 */
public interface ViewListener {
    void onButtonClickedEvent(ActionEvent e);

    void onWindowsCloseEvent(ActionEvent e);

    void onWindowResizeEvent(ActionEvent e);
}
