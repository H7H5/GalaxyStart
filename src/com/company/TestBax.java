package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by admin on 02.06.2020.
 */
public class TestBax {
    Image img3 = new ImageIcon("res/multi_bax.png").getImage();
    int frame = 0;
    int d =0;
    int e=2;
    boolean delete = false;
    int x;
    int y;

    public TestBax(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(){
        if (delete==false) {
            d++;
            if (d == e) {
                frame++;
                if (frame >= 81) {

                    delete = true;
                }
                d = 0;
            }
        }
    }
    public void paint (Graphics graphics){
        drawSpriteFrame(img3, graphics, x, y, 9, frame, 100, 100);
    }
    void drawSpriteFrame(Image source, Graphics g2d, int x, int y, int columns, int frame, int width, int height)
    {
        int frameX = (frame % columns) * width;
        int frameY = (frame / columns) * height;
        g2d.drawImage(source, x, y, x+width, y+height,
                frameX, frameY, frameX+width, frameY+height, null);
    }
}
