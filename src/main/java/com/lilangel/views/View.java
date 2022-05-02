package com.lilangel.views;

import com.lilangel.presenters.ViewListener;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * MVP View component, responsible for drawing and telling Presenter about User interaction with app
 */
public interface View {
    /**
     * Method that starts the main app window {@link MainApplicationFrame}
     */
    void notifyListeners(ActionEvent e);

    void setListener(ViewListener listener);

    ReturnCode addDrawEvent(ActionEvent e);
}
