package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by admin on 21.05.2020.
 */
public class EnemyBrown extends Enemy {

    public EnemyBrown(int x, int y) {

        range = random.nextInt(150)+50;
        this.x=x;
        this.y=y;
        xLeft = x - range;
        xRight = x + range;
        img = new ImageIcon("res/enemy21.png").getImage();
        dx = img.getWidth(null)/2;
        dy = img.getHeight(null)/2+10;
        range = 100;
        health = 100;
    }
    @Override
    public void update() {
        if (driftMove) {
            if (drift) {
                x = x - speed;
            } else {
                x = x + speed;
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
        randomShooting = random.nextInt(300);
        if (randomShooting==0) {
            Space.snarryadEnemy.add(new SnarryadEnemy1(x + dx, y+dy));
            randomShooting = 1;
        }
    }
    @Override
    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(x+10,y+5,img.getWidth(null)-20,img.getHeight(null)-20);
        return rectangle;
    }

}
