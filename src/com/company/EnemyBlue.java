package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by admin on 21.05.2020.
 */
public class EnemyBlue extends Enemy{
    public EnemyBlue(int x, int y) {
        super(); range = random.nextInt(150)+50;
        this.x=x;
        this.y=y;
        xLeft = x - range;
        xRight = x + range;
        img = new ImageIcon("res/enemy31.png").getImage();
        dx = img.getWidth(null)/2;
        range = 100;
        health = 20;
    }
    @Override
    public void update() {
        dyR = random.nextInt(16);
        if (dyR==0) {
            dy = random.nextInt(2);
        }
        if (driftMove) {
            if (drift) {
                x = x - speed;
            } else {
                x = x + speed;
            }
                if (dy == 0) {
                    y = y - speed;
                    if (y<0){
                        y = y + speed;
                        dyR=0;
                    }
                } else {
                    y = y + speed;
                    if (y>500){
                        y = y - speed;
                        dyR=0;
                    }
                }
            if(x<xLeft||x>xRight){
                drift = !drift;
                driftMove = false;
                timeSpent = System.currentTimeMillis();
            }
        }else {
            if ((System.currentTimeMillis()-timeSpent)>1000){
                driftMove = true;
            }
        }
        randomShooting = random.nextInt(100);
        if (randomShooting==0) {
            Space.snarryadEnemy.add(new SnarryadEnemy1(x + dx, y+dy));
            randomShooting = 1;
        }
    }
    @Override
    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(x+6,y+12,
                img.getWidth(null)-20,img.getHeight(null)-24);
        return rectangle;
    }
}
