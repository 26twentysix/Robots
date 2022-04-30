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
        for (ModelListener presenter : presenters) presenter.onModelUpdateEvent(new ActionEvent(this, 0, message));

//        ModelUpdateEvent event = new ModelUpdateEvent(this,1,message,field.makeCopyOfCurrentState());
//        int returnCode = presenters.onModelUpdateEvent(event);
//        if(returnCode == 1){
//            try {
//                // TODO переделать на invokeLater чтобы модель не затыкалась в этом методе из-за медленности отрисовки
//                Thread.sleep(10);
//                returnCode = presenters.onModelUpdateEvent(new ModelUpdateEvent(this,1,message,field.makeCopyOfCurrentState()));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
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
    public Robot getObservableRobot() {
        return field.getObservableRobot();
    }
}
