package com.lilangel.presenters;

import com.lilangel.views.View;
import com.lilangel.models.Model;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MonitorPresenter implements  ViewListener, ModelListener{

    Model model;

    public void setModel(Model model){
        this.model = model;
    }

    View view;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onModelUpdateEvent(ActionEvent e) {

    }

    @Override
    public void onViewEvent(ActionEvent e) {

    }
}
