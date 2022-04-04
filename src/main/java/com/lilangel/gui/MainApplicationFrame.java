package com.lilangel.gui;

import com.lilangel.log.Logger;
import com.lilangel.presenter.ViewListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Что требуется сделать:
 * 1. Метод создания меню перегружен функционалом и трудно читается.
 * Следует разделить его на серию более простых методов (или вообще выделить отдельный класс).
 */
public class MainApplicationFrame extends JFrame {
    private final JDesktopPane desktopPane = new JDesktopPane();

    private final GameWindow view;

    public ModelView getView() {
        return this.view;
    }

    public MainApplicationFrame(ViewListener presenter) {
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        setContentPane(desktopPane);

        this.view = createGameWindow(presenter);
        addWindow(view);
        addWindow(createLogWindow());

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    protected GameWindow createGameWindow(ViewListener presenter) {
        GameWindow gameWindow = new GameWindow(presenter);
        gameWindow.setLocation(310, 10);
        gameWindow.setSize(400, 400);
        return gameWindow;
    }

    protected LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10, 10);
        logWindow.setSize(300, 800);
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

//    protected JMenuBar createMenuBar() {
//        JMenuBar menuBar = new JMenuBar();
// 
//        //Set up the lone menu.
//        JMenu menu = new JMenu("Document");
//        menu.setMnemonic(KeyEvent.VK_D);
//        menuBar.add(menu);
// 
//        //Set up the first menu item.
//        JMenuItem menuItem = new JMenuItem("New");
//        menuItem.setMnemonic(KeyEvent.VK_N);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("new");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
// 
//        //Set up the second menu item.
//        menuItem = new JMenuItem("Quit");
//        menuItem.setMnemonic(KeyEvent.VK_Q);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("quit");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
// 
//        return menuBar;
//    }

    private JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu lookAndFeelMenu = createMenu("Режим отображения", KeyEvent.VK_V,
                "Управление режимом отображения приложения");
        JMenuItem systemLookAndFeel = createMenuItem("Системная схема", KeyEvent.VK_S,
                "System");
        JMenuItem crossPlatformLookAndFeel = createMenuItem("Универсальная схема", KeyEvent.VK_S,
                "CrossPlatform");
        JMenu testMenu = createMenu("Тесты", KeyEvent.VK_T, "Тестовые команды");
        JMenuItem addLogMessageItem = createMenuItem("Сообщение в лог", KeyEvent.VK_S,
                "LoggerDebug");
        lookAndFeelMenu.add(crossPlatformLookAndFeel);
        lookAndFeelMenu.add(systemLookAndFeel);
        testMenu.add(addLogMessageItem);
        menuBar.add(lookAndFeelMenu);
        menuBar.add(testMenu);
        return menuBar;
    }

    private JMenu createMenu(String menuName, int key, String description) {
        JMenu newMenu = new JMenu(menuName);
        newMenu.setMnemonic(key);
        newMenu.getAccessibleContext().setAccessibleDescription(description);
        return newMenu;
    }

    private JMenuItem createMenuItem(String menuItemName, int key, String additionalArgument) {
        JMenuItem newMenuItem = new JMenuItem(menuItemName, key);
        newMenuItem.addActionListener((event) -> {
            switch (additionalArgument.toLowerCase(Locale.ROOT)) {
                case "crossplatform" -> {
                    setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    this.invalidate();
                }
                case "system" -> {
                    setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    this.invalidate();
                }
                case "loggerdebug" -> Logger.debug("New string");
            }
        });
        return newMenuItem;
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            // just ignore
        }
    }
}
