package com.lilangel.views.game;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.ReturnCode;
import com.lilangel.views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;

/**
 * Class that visualize the Model through MVP pattern (for now it doesn't)
 */
public class GameVisualizer extends JPanel implements View {
    private final Drawer drawer;

    private final Queue<FieldDrawEvent> drawQueue = new ArrayDeque<>();

    private ViewListener listener;

    public void setListener(ViewListener listener){
        this.listener = listener;
    }

    public GameVisualizer() {
        this.drawer = new Drawer();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                notifyListeners(new ActionEvent(e.getSource(),0,e.paramString()));
                /*
                 В этом методе надо обрабатывать нажатие мыши, на какие координаты, по ним вычислять клетку
                 и потом эту клетку в ActionEvent'е отправлять в notifyPresenter().
                 Возможно хорошей идеей будет отнаследовать свой класс адаптер и в нем логику прописывать для действий мыши.
                 Аналогинчо для кнопок, для них только отдельные адаптеры-листенеры есть,
                 кнопки в конструкторе или в приватном вспомогательном инит методе создаешь и им листенеры добавляешь
                 В каждом методе-обработчике собирается ActionEvent с описанием че произошло, этот ивент отправляется в
                 notifyPresenter, оттуда он попадает в onViewEvent, в которм уже будет диспатчер, который будет обрабатывать
                 ActionEvent'ы полученные
                */
            }
        });
        this.setDoubleBuffered(true);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            onRedrawEvent();
        }
    };

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (!drawQueue.isEmpty()) {
            var state = drawQueue.poll();
            drawer.draw(g2d, state.getField());
        }
    }

    @Override
    public void notifyListeners(ActionEvent e) {
        listener.onViewEvent(e);
    }

    @Override
    public ReturnCode addDrawEvent(ActionEvent e) {
        int DRAW_QUEUE_SIZE = 1000;
        if (drawQueue.size() <= DRAW_QUEUE_SIZE) {
            this.drawQueue.add((FieldDrawEvent) e);
            return ReturnCode.OK;
        }
        return ReturnCode.QUEUE_IS_FULL;
    }
}
