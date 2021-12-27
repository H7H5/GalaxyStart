package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by admin on 04.06.2020.
 */
public class BossComponent {
    Image img3= new ImageIcon("res/boss1png.png").getImage();
    int x;
    int dx=0;
    int dy=0;
    int y;
    int d;
    int frame;
    int mode = 1;
    int randomShooting = 1;
    Random random = new Random();
    public BossComponent(int x, int y,int frame){
        this.frame = frame;
        this.x = x;
        this.y = y;
        switch (frame){
            case 5:
                dx = 127;
                dy =  330;
                break;
            case 10:
                dx = 320;
                dy =  330;
                break;
            case 15:
                dx = 90;
                dy =  310;
                break;
            case 20:
                dx = 390;
                dy = 310;
                break;
            case 25:
                dx = 155;
                dy = 165;
                break;
            case 30:
                dx = 340;
                dy = 165;
                break;
            case 35:
                dx = 110;
                dy = 60;
                break;
            case 40:
                dx = 350;
                dy = 60;
                break;
        }
    }
    public void paint (Graphics graphics){
        drawSpriteFrame(img3, graphics, x, y, 5, frame + d, 529, 556);
    }
    void drawSpriteFrame(Image source, Graphics g2d, int x, int y, int columns, int frame, int width, int height)
    {
        int frameX = (frame % columns) * width;
        int frameY = (frame / columns) * height;
        g2d.drawImage(source, x, y, x+width, y+height,
                frameX, frameY, frameX+width, frameY+height, null);
    }
    public void update(int x, int y, int d){
        if (mode == 1) {
            this.x = x;
            this.y = y;
            this.d = d;
            randomShooting = random.nextInt(150);
            if (randomShooting==0) {
                int rn = random.nextInt(10);
                if (rn == 1){
                    Space.snarryadEnemy.add(new SnarryadEnemy2(x + dx, y+dy));
                }else {
                    Space.snarryadEnemy.add(new SnarryadEnemy1(x + dx, y+dy));
                }

                randomShooting = 1;
            }
        }else {
            this.y = this.y +1;
        }

    }
}
