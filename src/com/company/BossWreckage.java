package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BossWreckage {
    Image img3= new ImageIcon("res/bossBax.png").getImage();
    int x;
    int y;
    int frame;
    int speed = 1;

    public BossWreckage(int x, int y, int frame){
        this.frame = frame;
        this.x = x;
        this.y = y;
    }
    public void paint (Graphics graphics){
        drawSpriteFrame(img3, graphics, x, y, 5, frame, 529, 556);
    }
    void drawSpriteFrame(Image source, Graphics g2d, int x, int y, int columns, int frame, int width, int height)
    {
        int frameX = (frame % columns) * width;
        int frameY = (frame / columns) * height;
        g2d.drawImage(source, x, y, x+width, y+height,
                frameX, frameY, frameX+width, frameY+height, null);
    }
    public void update(){
        switch (frame){
            case 0:
                x = x - speed*2;
                y = y - speed;
                break;
            case 1:
                x = x + speed*2;
                y = y - speed;
                break;
            case 2:
                x = x - speed*2;
                y = y + speed;
                break;
            case 3:
                x = x + speed*2;
                y = y + speed;
                break;
        }
    }
}
