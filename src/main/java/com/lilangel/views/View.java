package com.lilangel.views;

import java.awt.event.ActionEvent;

/**
 * MVP View component, responsible for drawing and telling Presenter about User interaction with app
 */
public interface View {
    /**
     * Method that starts the main app window {@link MainApplicationFrame}
     */
    void notifyPresenter(ActionEvent e);

    int addDrawEvent(ActionEvent e);
}
