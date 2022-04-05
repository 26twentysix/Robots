package com.lilangel.gui;

import java.awt.event.ActionEvent;

/**
 * MVP View component, responsible for drawing and telling Presenter about User interaction with app
 */
public interface ModelView {
    /**
     * Method that starts the main app window {@link MainApplicationFrame}
     */
    void notifyPresenter(ActionEvent e);

    void update();

    int addDrawEvent(ActionEvent e);
}
