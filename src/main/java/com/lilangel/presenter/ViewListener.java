package com.lilangel.presenter;

import java.awt.event.ActionEvent;

public interface ViewListener {
    void onButtonClickedEvent(ActionEvent e);
    void onWindowsCloseEvent(ActionEvent e);
    void onWindowResizeEvent(ActionEvent e);
}
