package com.lilangel.gui;

import com.lilangel.presenter.ViewListener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.text.View;

public class GameWindow extends JInternalFrame implements ModelView
{
    private final GameVisualizer visualizer;
    private final ViewListener presenter;

    public GameWindow(ViewListener presenter) {
        super("Игровое поле", true, true, true, true);
        this.presenter = presenter;
        this.visualizer = new GameVisualizer(this);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    @Override
    public void notifyPresenter(ActionEvent e) {
        switch (e.getActionCommand()){
            case "mouseEvent" : presenter.onButtonClickedEvent(e);
        }
    }

    @Override
    public void update() {
        visualizer.onRedrawEvent();
    }
}
