package com.lilangel.presenters;

import com.lilangel.models.FieldStateModel;
import com.lilangel.views.*;
import com.lilangel.views.game.main.FieldDrawEvent;
import com.lilangel.views.game.enums.GameSpeed;
import com.lilangel.views.game.main.FieldClickEvent;
import com.lilangel.views.game.settings.ButtonClickEvent;
import com.lilangel.views.game.settings.SpeedModeButtonDrawEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

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

    Timer gameViewTimer;

    public GamePresenter(MainApplicationFrame frame) {
        this.gameView = frame.getGameVisualizer();
        this.settingView = frame.getGameSettingsVisualizer();
        this.gameView.setListener(this);
        this.settingView.setListener(this);

        this.gameViewTimer = new Timer(true);

        new Timer(true).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                settingView.update();
            }
        }, 0, 16);
        new Timer(true).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameView.update(drawQueue.poll());
            }
        }, 0, 16);
    }

    @Override
    public void onModelUpdateEvent(ActionEvent e) {
        if (e == null || "no".equals(e.getActionCommand())) return;

        FieldDrawEvent event = new FieldDrawEvent(e.getSource(), 0, "redraw idk", model.getField());
        ReturnCode code = addDrawEvent(event);

        if (code != ReturnCode.OK)
            EventQueue.invokeLater(() -> addDrawEvent(event));
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
        Source source = (Source) e.getSource();
        switch (source) {
            case GAME_VISUALIZER -> gameVisualizerEventHandler(e);
            case GAME_SETTINGS_VISUALIZER -> gameSettingsVisualizerEventHandler(e);
            case MONITOR_VISUALIZER -> {
                //igonre
            }
        }
    }

    private void gameVisualizerEventHandler(ActionEvent e) {
        if (e != null)
            if (e instanceof FieldClickEvent event) {
                model.selectRobot(event.getTile().x, event.getTile().y);
                System.out.println("handler " + event.getTile().x + " " + event.getTile().y);
            }
    }

    private int i = 0;
    private int previousI = 3;
    Map<Integer, GameSpeed> speedMap = new HashMap<>() {{
        put(5, GameSpeed.VERY_FAST);
        put(4, GameSpeed.FAST);
        put(3, GameSpeed.NORMAL);
        put(2, GameSpeed.SLOW);
        put(1, GameSpeed.VERY_SLOW);
        put(0, GameSpeed.PAUSE);
    }};

    private void gameSettingsVisualizerEventHandler(ActionEvent e) {
        if (e != null)
            if (e instanceof ButtonClickEvent event) {
                switch (event.getEvent()) {
                    case CLICK_ON_PAUSE_BUTTON -> {
                        previousI = i;
                        i = 0;
                    }
                    case CLICK_ON_PLAY_BUTTON -> i = previousI;
                    case CLICK_ON_GAME_SPEED_UP -> {
                        if (i < 5) i++;
                    }
                    case CLICK_ON_GAME_SPEED_DOWN -> {
                        if (i > 0) i--;
                    }
                }
                gameViewTimer.cancel();
                gameViewTimer = new Timer(true);
                GameSpeed speed = speedMap.get(i);
                settingView.update(new SpeedModeButtonDrawEvent(this, 0, "", speed));
                model.setTactSpeed(speed);
            }
    }

}
