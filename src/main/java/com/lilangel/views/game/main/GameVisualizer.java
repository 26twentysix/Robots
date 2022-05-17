package com.lilangel.views.game.main;

import com.lilangel.presenters.ViewListener;
import com.lilangel.views.DrawEvent;
import com.lilangel.views.Source;
import com.lilangel.views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class that visualize the Model through MVP pattern (for now it doesn't)
 */
public class GameVisualizer extends JPanel implements View {
    private final Drawer drawer;

    private FieldDrawEvent drawEvent;

    private ViewListener listener;

    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    public GameVisualizer() {
        this.drawer = new Drawer();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Point currentPosition = e.getPoint();
                    notifyListeners(new FieldClickEvent(Source.GAME_VISUALIZER, 0, e.paramString(),
                            new Point(currentPosition.x / 16, currentPosition.y / 16)));
                }
            }
        });
        this.setDoubleBuffered(true);
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (drawEvent != null) {
            drawer.draw(g2d, drawEvent.getField());
        }
    }

    @Override
    public void notifyListeners(ActionEvent e) {
        listener.onViewEvent(e);
    }

    @Override
    public void update() {

    }

    @Override
    public void update(DrawEvent event) {
        if (event instanceof FieldDrawEvent) {
            this.drawEvent = (FieldDrawEvent) event;
            onRedrawEvent();
        }
    }
}
