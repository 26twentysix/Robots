package com.lilangel.model;

import com.lilangel.log.Logger;
import com.lilangel.presenter.ModelListener;

/**
 * Class that describes the model of battleground
 */
public class RobotsModel implements Model {
    /**
     * Presenter that model connected with
     */
    private ModelListener presenter;

    private final Field field;

    public RobotsModel(){
        this.field = new Field();
    }

    @Override
    public void setPresenter(ModelListener presenter) {
        this.presenter=presenter;
    }

    @Override
    public void notifyPresenter(String message) {
        presenter.onModelUpdateEvent(new ModelUpdateEvent(this,1,message,field.makeCopyOfCurrentState()));
    }

    @Override
    public void run() {
        while(field.isAnyRobotAlive()){
            field.doTact();
            notifyPresenter(field.hasFieldChanged());
        }
        System.out.println("simulation finished");
    }
}
