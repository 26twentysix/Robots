package com.lilangel.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class that visualize the Model through MVP pattern (for now it doesn't)
 */
public class GameVisualizer extends JPanel
{
    private final Drawer drawer;

    public GameVisualizer(GameWindow gameWindow)
    {
        this.drawer = new Drawer();
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                gameWindow.notifyPresenter(new ActionEvent(e, 1,"mouseEvent"));
            }
        });
        setDoubleBuffered(true);
    }

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
