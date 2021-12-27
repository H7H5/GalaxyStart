package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 20.05.2020.
 */
public class Snarryad {
    int damage = 20;
    Image img = new ImageIcon("res/snaryad.png").getImage();
    int x;
    int y;
    int v = 3;
    int dxSnarryad = img.getWidth(null)/2;
    public Snarryad(int x, int y){
        this.x = x - dxSnarryad;
        this.y = y;
    }
    public void paint (Graphics g){
        g.drawImage(img,x,y,img.getWidth(null),img.getHeight(null),null);
        //g.fillRect(getRectangle().x,getRectangle().y,getRectangle().width,getRectangle().height);
    }
    public void update(){
        y = y -v;
    }

    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
        return rectangle;
    }

}
