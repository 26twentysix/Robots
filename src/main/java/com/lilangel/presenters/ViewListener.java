package com.lilangel.presenters;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * Intefrace that declare what events View can invoke and Presenter must handle
 */
public interface ViewListener {
    void onViewEvent(ActionEvent e);
}
