package com.lilangel.views.monitor.drawer;

import com.lilangel.models.robot.Robot;

import java.awt.*;

public class NormalDrawingScheme implements DrawingScheme {
    @Override
    public void draw(Robot robot, Graphics2D g2d) {
        String result = """
                Position: x = %d , y = %d \n
                Is alive: %s \n
                Energy: %f / %d \n
                Healthpoints: %d %d
                """.formatted(robot.getPositionX(), robot.getPositionY(), String.valueOf(robot.isAlive()), robot.getEnergy(), robot.getMaxEnergy(), robot.getHealthpoints(), robot.getMaxHealthPoints());
        g2d.drawString("Position: x = %d , y = %d".formatted(robot.getPositionX(), robot.getPositionY()), 5, 20);
        g2d.drawString("Is alive: %s".formatted(String.valueOf(robot.isAlive())), 5, 35);
        g2d.drawString("Energy: %f / %d".formatted(robot.getEnergy(), robot.getMaxEnergy()), 5, 50);
        g2d.drawString("Healthpoints: %d %d".formatted(robot.getHealthpoints(), robot.getMaxHealthPoints()), 5, 65);
    }
}
