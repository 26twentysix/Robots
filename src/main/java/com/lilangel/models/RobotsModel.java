package com.lilangel.models;

import com.lilangel.models.enums.ObjectOnTile;
import com.lilangel.models.robot.Robot;
import com.lilangel.presenters.ModelListener;

import java.awt.event.ActionEvent;

/**
 * Class that describes the model of battleground
 */
public class RobotsModel implements Model, FieldStateModel, MonitorModel{
    /**
     * Presenter that model connected with
     */
    private ModelListener[] presenters;

    private final Field field;

    public RobotsModel() {
        this.field = new Field();
    }

    @Override
    public void setPresenters(ModelListener[] presenters) {
        this.presenters = presenters;
    }

    @Override
    public void notifyPresenter(String message) {
        for (ModelListener presenter : presenters)
            presenter.onModelUpdateEvent(new ActionEvent(this, 0, message));
    }

    @Override
    public void run() {
        while (field.isAnyRobotAlive()) {
            field.doTact();
            notifyPresenter(field.fieldChanges());
        }
        System.out.println("simulation finished");
    }

    @Override
    public ObjectOnTile[][] getField() {
        return field.makeCopyOfCurrentState();
    }

    @Override
    public void selectRobot(int x, int y) {
        field.observeRobot(x,y);
    }

    @Override
    public Robot getObservableRobot() {
        return field.getObservableRobot();
    }
}
