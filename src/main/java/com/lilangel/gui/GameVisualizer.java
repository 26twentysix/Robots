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

public class GameVisualizer extends JPanel
{
    private final Timer m_timer = initTimer();
    private Drawer drawer;
    private Presenter presenter;
    
    private static Timer initTimer() 
    {
        Timer timer = new Timer("events generator", true);
        return timer;
    }
    
    public GameVisualizer(Presenter presenter)
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
    
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }
    
    private static int round(double value)
    {
        return (int)(value + 0.5);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
    }
}
