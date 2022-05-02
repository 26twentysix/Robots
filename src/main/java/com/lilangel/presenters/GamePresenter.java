package com.lilangel.presenters;

import com.lilangel.models.FieldStateModel;
import com.lilangel.views.DrawEvent;
import com.lilangel.views.MainApplicationFrame;
import com.lilangel.views.ReturnCode;
import com.lilangel.views.View;
import com.lilangel.views.game.FieldDrawEvent;
import com.lilangel.views.game.enums.GameSpeed;

import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * MVP Presenter component, responsible for updating View and giving tasks for Model
 */
public class GamePresenter implements ModelListener, ViewListener {
    FieldStateModel model;

    public void setModel(FieldStateModel model) {
        this.model = model;
    }

    View gameView;
    private final Queue<FieldDrawEvent> drawQueue = new ArrayDeque<>();

    View settingView;

    public GamePresenter(MainApplicationFrame frame){
        this.gameView = frame.getGameVisualizer();
        this.settingView = frame.getGameSettingsVisualizer();
        this.gameView.setListener(this);
        this.settingView.setListener(this);
        new Timer(true).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                settingView.update(new DrawEvent(new Object(),0,"nothing"));
            }
        },0,16);
        scheduleGameViewUpdate(GameSpeed.NORMAL);
    }

    private void scheduleGameViewUpdate(GameSpeed speed){
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!drawQueue.isEmpty())
                    gameView.update(drawQueue.poll());
            }
        }, 0, (int) (100 * speed.value));
    }

    @Override
    public void onModelUpdateEvent(ActionEvent e) {
        if (e == null || "no".equals(e.getActionCommand())) return;

        FieldDrawEvent event = new FieldDrawEvent(e.getSource(),0,"redraw idk", model.getField());
        ReturnCode code = addDrawEvent(event);

        while (code == ReturnCode.QUEUE_IS_FULL){
            try {
                Thread.sleep(10);
                code = addDrawEvent(event);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private ReturnCode addDrawEvent(ActionEvent e) {
        int DRAW_QUEUE_SIZE = 1000;
        if (drawQueue.size() <= DRAW_QUEUE_SIZE) {
            this.drawQueue.add((FieldDrawEvent) e);
            return ReturnCode.OK;
        }
        return ReturnCode.QUEUE_IS_FULL;
    }

    @Override
    public void onViewEvent(ActionEvent e) {
        //TODO вот тут должен быть диспатчер
    }
}
