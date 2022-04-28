package com.lilangel.presenter;

import com.lilangel.gui.ModelView;
import com.lilangel.models.Model;

import java.awt.event.ActionEvent;

import com.lilangel.execpions.OurNotImplemetedException;

/**
 * MVP Presenter component, responsible for updating View and giving tasks for Model
 */
public class Presenter implements ModelListener, ViewListener {
    Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    ModelView view;

    public void setView(ModelView view) {
        this.view = view;
    }

    /**
     * Timer for redrawing
     */

    public Presenter() {

    }

    @Override
    public int onModelUpdateEvent(ActionEvent e) {
        return view.addDrawEvent(e);
    }

    @Override
    public void onButtonClickedEvent(ActionEvent e) {
        throw new OurNotImplemetedException();
    }

    @Override
    public void onWindowsCloseEvent(ActionEvent e) {
        throw new OurNotImplemetedException();

    }

    @Override
    public void onWindowResizeEvent(ActionEvent e) {
        throw new OurNotImplemetedException();

    }
}
