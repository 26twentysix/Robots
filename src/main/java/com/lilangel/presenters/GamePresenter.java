package com.lilangel.presenters;

import com.lilangel.models.FieldStateModel;
import com.lilangel.models.Model;
import com.lilangel.views.View;

import java.awt.event.ActionEvent;

import com.lilangel.execpions.OurNotImplemetedException;
import com.lilangel.models.RobotsModel;
import com.lilangel.views.game.FieldDrawEvent;

/**
 * MVP Presenter component, responsible for updating View and giving tasks for Model
 */
public class GamePresenter implements ModelListener, ViewListener {
    FieldStateModel model;

    public void setModel(FieldStateModel model) {
        this.model = model;
    }

    View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public int onModelUpdateEvent(ActionEvent e) {
        if ("no".equals(e.getActionCommand())) return -1;
        FieldDrawEvent event = new FieldDrawEvent(e.getSource(),0,"redraw idk", model.getField());
        return view.addDrawEvent(event);
    }

    @Override
    public void onViewEvent(ActionEvent e) {
        //TODO вот тут должен быть диспатчер
        throw new OurNotImplemetedException();
    }

}
