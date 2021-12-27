package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by admin on 07.06.2020.
 */
public class Health {
    public int x = 10;
    public int y = 900;
    public int speed = 0;
    public int  life = 200;
    public int  armor = 200;
    Image img = new ImageIcon("res/h0.png").getImage();
    Image img1 = new ImageIcon("res/a0.png").getImage();
    Image img2 = new ImageIcon("res/v0.png").getImage();


    public void update(){
    }
    public void paint (Graphics graphics){

        graphics.drawImage(img2,x,y,201,17,null);
        graphics.drawImage(img1,x+1,y+1,armor,16,null);
        graphics.drawImage(img2,x,y+30,201,17,null);
        graphics.drawImage(img,x+1,y+30 +1,life,16,null);



    }
}
