package com.lilangel;

import com.lilangel.gui.View;
import com.lilangel.model.Model;
import com.lilangel.presenter.Presenter;

/**
 * Main app class, that initialize Model, View and Presenter and starts the things
 */
public class RobotApp {
    Model model;
    View view;
    Presenter presenter;

    public RobotApp(){
        this.presenter = new Presenter();
        this.model = new Model(presenter);
        this.view = new View(presenter);
        presenter.setModel(model);
        presenter.setView(view);
    }

    public void startApp(){
        this.view.start();
    }
}
