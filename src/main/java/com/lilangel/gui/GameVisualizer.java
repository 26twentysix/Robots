package com.lilangel.gui;

import com.lilangel.model.ModelState;
import com.lilangel.presenter.Presenter;
import com.lilangel.presenter.ViewListener;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/**
 * Class that visualize the Model through MVP pattern (for now it doesn't)
 */
public class GameVisualizer extends JPanel
{
    private final Drawer drawer;
    private final ViewListener presenter;
    
    public GameVisualizer(ViewListener presenter)
    {
        this.presenter = presenter;
        this.drawer = new Drawer();
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                presenter.onButtonClickedEvent(new ActionEvent(this,1,"click"));
            }
        });
        setDoubleBuffered(true);
    }

    //Я думаю это должно быть в презентере, но сейчас у меня голова сломалась, я два часа думал как эту хуйню разобрать
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        drawer.draw(g2d);
    }
}
