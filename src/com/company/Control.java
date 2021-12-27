package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter {

    Hero hero;
    public Control(Hero h) {
        this.hero = h;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        hero.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {

        super.keyReleased(e);
        hero.keyReleased(e);
    }
}
