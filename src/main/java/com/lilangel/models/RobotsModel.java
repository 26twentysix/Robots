package com.lilangel.models;

import com.lilangel.models.enums.ObjectOnTile;
import com.lilangel.models.robot.Robot;
import com.lilangel.presenters.ModelListener;
import com.lilangel.views.game.enums.GameSpeed;

import java.awt.event.ActionEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that describes the model of battleground
 */
public class RobotsModel implements Model, FieldStateModel, MonitorModel{
    /**
     * Presenter that model connected with
     */
    private ModelListener[] presenters;

    private final Field field;

    private GameSpeed tactSpeed;
    private final Object lockObj = new Object();

    public void setTactSpeed(GameSpeed speed){
        this.tactSpeed = speed;
        synchronized (lockObj){
            lockObj.notify();
        }
    }

    public RobotsModel() {
        this.field = new Field();
        this.tactSpeed = GameSpeed.PAUSE;
    }

    @Override
    public void setPresenters(ModelListener[] presenters) {
        this.presenters = presenters;
    }

    @Override
    public void notifyPresenter(String message) {
        for (ModelListener presenter : presenters) {
            presenter.onModelUpdateEvent(new ActionEvent(this, 0, message));
        }
    }

    @Override
    public void run() {
        while (field.isAnyRobotAlive()) {
            synchronized (lockObj){
                try {
                    lockObj.wait((int)(tactSpeed.value*100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
        notifyPresenter("");
    }

    @Override
    public Robot getObservableRobot() {
        return field.getObservableRobot();
    }
}
