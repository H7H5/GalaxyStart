package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by admin on 21.05.2020.
 */
public class EnemyDark extends Enemy{
    public EnemyDark(int x, int y) {
        super();
        this.x=x;
        this.y=y;
        range = random.nextInt(150)+50;
        xLeft = x - range;
        xRight = x + range;
        img = new ImageIcon("res/enemy11.png").getImage();
        dx = img.getWidth(null)/2;
        range = 20;
        health = 60;
    }
    @Override
    public void update() {
        dyR = random.nextInt(1000);
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
        randomShooting = random.nextInt(200);
        if (randomShooting==0) {
            Space.snarryadEnemy.add(new SnarryadEnemy1(x + dx, y+dy));
            randomShooting = 1;
        }
    }
    @Override
    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(x+15,y,img.getWidth(null)-30,
                img.getHeight(null)-30);
        return rectangle;
    }



}
