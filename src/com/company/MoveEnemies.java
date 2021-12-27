package com.company;

import java.awt.*;

/**
 * Created by admin on 21.05.2020.
 */
public interface MoveEnemies {
    public void update();
    public void paint (Graphics graphics);
    public Rectangle getRectangle();
    public boolean getHit();
    public void setHit(boolean b);
    public void attack(int damade);
    public int getX();
    public int getY();

}
