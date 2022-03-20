package com.lilangel.presenter;

import com.lilangel.gui.View;
import com.lilangel.model.Model;

import jdk.jshell.spi.ExecutionControl;

import java.awt.event.ActionEvent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * MVP Presenter component, responsible for updating View and giving tasks for Model
 */
public class Presenter implements ModelListener, ViewListener {
    Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    View view;

    public void setView(View view) {
        this.view = view;
    }

    /**
     * Timer for redrawing
     */
    private final Timer timer = initTimer();

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    public Presenter() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 50);
    }

    protected void onRedrawEvent() {

    }

    @Override
    public void onModelUpdateEvent(ActionEvent e) {
        try {
            throw new ExecutionControl.NotImplementedException("не реализованы действия на обновление модели");
        } catch (ExecutionControl.NotImplementedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onButtonClickedEvent(ActionEvent e) {
        try {
            throw new ExecutionControl.NotImplementedException("не реализованы дейсвтия на кнопки");
        } catch (ExecutionControl.NotImplementedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onWindowsCloseEvent(ActionEvent e) {
        try {
            throw new ExecutionControl.NotImplementedException("не реализован ивент на закрытие окна");
        } catch (ExecutionControl.NotImplementedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onWindowResizeEvent(ActionEvent e) {
        try {
            throw new ExecutionControl.NotImplementedException("не реализовано изменение размера окна");
        } catch (ExecutionControl.NotImplementedException ex) {
            ex.printStackTrace();
        }
    }
}
