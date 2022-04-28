package com.lilangel.models;

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
        ModelUpdateEvent event = new ModelUpdateEvent(this,1,message,field.makeCopyOfCurrentState());
        int returnCode = presenter.onModelUpdateEvent(event);
        if(returnCode == 1){
            try {
                // TODO переделать на invokeLater чтобы модель не затыкалась в этом методе из-за медленности отрисовки
                Thread.sleep(10);
                returnCode = presenter.onModelUpdateEvent(new ModelUpdateEvent(this,1,message,field.makeCopyOfCurrentState()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while(field.isAnyRobotAlive()){
            field.doTact();
            notifyPresenter(field.fieldChanges());
        }
        System.out.println("simulation finished");
    }
}
