package com.lilangel.gui;

import com.lilangel.model.ModelUpdateEvent;
import com.lilangel.model.ObjectOnTile;

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
public class GameVisualizer extends JPanel implements ModelView {
    private final Drawer drawer;

    private final int DRAW_QUEUE_SIZE = 1000;

    private Queue<ModelUpdateEvent> drawQueue = new ArrayDeque<>();

    public GameVisualizer(GameWindow gameWindow) {
        this.drawer = new Drawer();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notifyPresenter(new ActionEvent(e, 1, "mouseEvent"));
            }
        });
        setDoubleBuffered(true);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, 300);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            onRedrawEvent();
        }
    };

    protected void onRedrawEvent() { // вот этот метод должен вызываться в цикле
        EventQueue.invokeLater(this::repaint);
    }

//    ModelUpdateEvent previousState = new ModelUpdateEvent(this,0,"",new ObjectOnTile[200][300]);
//    int i = 0;

    @Override
    public void paint(Graphics g) { // вот этот метод умеет рисовать один кадр, используя для этого Drawer
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (!drawQueue.isEmpty()) {
            var state = drawQueue.poll();
//            System.out.print(i++);
//            if (!stateChanged(previousState.getField(), state.getField())) {
//                System.out.println(" state didnt changed");
//                previousState = state;
//            }
            // Drawer должен отрисовывать один кадр
            System.out.println("Получил поле");
            drawer.draw(g2d, state.getField());
//            previousState = state;
        }
    }

//    boolean stateChanged(ObjectOnTile[][] firstState, ObjectOnTile[][] secondState) {
//        for (int i = 0; i < firstState.length; i++)
//            for (int j = 0; j < firstState[i].length; j++)
//                if (firstState[i][j] != secondState[i][j]) {
//                    return true;
//                }
//        return false;
//    }

    @Override
    public void notifyPresenter(ActionEvent e) {

    }

    @Override
    public int addDrawEvent(ActionEvent e) {
        if (drawQueue.size() > DRAW_QUEUE_SIZE) {
            return 1;
        } else {
            this.drawQueue.add((ModelUpdateEvent) e);
            return 0;
        }
    }
}
