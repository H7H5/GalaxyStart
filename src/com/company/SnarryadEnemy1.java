package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 23.05.2020.
 */
public class SnarryadEnemy1 extends SnEn  {
    Image img = new ImageIcon("res/snarryadEnemy1.png").getImage();
    int x;
    int y;
    int v = 2;
    int dxSnarryad = img.getWidth(null)/2;
    public SnarryadEnemy1(int x, int y){
        super();
        this.x = x - dxSnarryad;
        this.y = y;
        damage = 10;
    }
    @Override
    public void paint (Graphics g){
        g.drawImage(img,x,y,img.getWidth(null),img.getHeight(null),null);
    }
    @Override
    public void update(){
        y = y + v;
    }
    @Override
    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
        return rectangle;
    }
}
