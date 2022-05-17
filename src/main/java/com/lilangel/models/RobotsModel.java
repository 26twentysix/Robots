package com.lilangel.models;

import com.lilangel.models.enums.ModelEventType;
import com.lilangel.models.enums.ObjectOnTile;
import com.lilangel.models.robot.Robot;
import com.lilangel.presenters.ModelListener;
import com.lilangel.models.enums.GameSpeed;

import java.awt.event.ActionEvent;

/**
 * Class that describes the model of battleground
 */
public class RobotsModel implements Model, FieldStateModel, MonitorModel {
    /**
     * Presenter that model connected with
     */
    private ModelListener[] presenters;

    private final Field field;

    private GameSpeed tactSpeed;
    private final Object lockObj = new Object();

    private boolean isEvolutionActive;

    public boolean changeEvolutionActive() {
        var oldValue = isEvolutionActive;
        isEvolutionActive = !oldValue;
        return isEvolutionActive;
    }

    public void setTactSpeed(GameSpeed speed) {
        this.tactSpeed = speed;
        synchronized (lockObj) {
            lockObj.notify();
        }
    }

    public RobotsModel() {
        this.field = new Field();
        this.tactSpeed = GameSpeed.PAUSE;
        isEvolutionActive = false;
    }

    @Override
    public void setPresenters(ModelListener[] presenters) {
        this.presenters = presenters;
    }

    @Override
    public void notifyPresenters(String message) {
        for (ModelListener presenter : presenters) {
            presenter.onModelUpdateEvent(new ActionEvent(this, 0, message));
        }
    }

    @Override
    public void run() {
        while (true) {
            doCycle();
            field.prepareNewGeneration(field.getAliveList());
            if (field.generationCount % 50 == 0)
                notifyPresenters(ModelEventType.CYCLE_FINISHED.value);
        }
//        System.out.println("simulation finished");
    }

    private void doCycle() {
        while (field.getAliveCount() > 10) {
            field.doTact();
            if (!(isEvolutionActive)) {
                synchronized (lockObj) {
                    try {
                        lockObj.wait((int) (tactSpeed.value * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notifyPresenters(ModelEventType.TACT_FINISHED.value);
            }
        }
    }

    @Override
    public ObjectOnTile[][] getField() {
        return field.makeCopyOfCurrentState();
    }

    @Override
    public void selectRobot(int x, int y) {
        field.observeRobot(x, y);
        notifyPresenters(ModelEventType.ROBOT_SELECTED.value);
    }

    @Override
    public Robot getObservableRobot() {
        return field.getObservableRobot();
    }
}
