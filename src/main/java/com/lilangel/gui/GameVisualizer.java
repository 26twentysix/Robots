package com.lilangel.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class that visualize the Model through MVP pattern (for now it doesn't)
 */
public class GameVisualizer extends JPanel implements ModelView {
    private final Drawer drawer;

    private final int DRAW_QUEUE_SIZE = 1000;

    private Queue<ActionEvent> drawQueue = new ArrayDeque<>();

    public GameVisualizer(GameWindow gameWindow) {
        this.drawer = new Drawer();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notifyPresenter(new ActionEvent(e, 1, "mouseEvent"));
            }
        });
        setDoubleBuffered(true);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawer.draw(g2d);
    }

    @Override
    public void notifyPresenter(ActionEvent e) {

    }

    @Override
    public int addDrawEvent(ActionEvent e) {
        if (drawQueue.size() > DRAW_QUEUE_SIZE) {
            return 1;
        }
        else {
            this.drawQueue.add(e);
            return 0;
        }
    }

    @Override
    public void update() {

    }
}
