package com.company;

import javax.swing.*;
import java.awt.*;

public class BackGround {
    Image img3 = new ImageIcon("res/ss.png").getImage();
    int universeY1 = 0;
    int universeY2 = img3.getHeight(null)*-1;
    float speed = 0.1f;
    float tempspeed = 0f;
    public void paint (Graphics graphics){
        graphics.drawImage(img3,0,universeY1,img3.getWidth(null),img3.getHeight(null),null);
        graphics.drawImage(img3,0,universeY2,img3.getWidth(null),img3.getHeight(null),null);
    }
    public void update(){
        tempspeed = tempspeed + speed;
        if (tempspeed>=1){
            tempspeed = tempspeed - 1;
            universeY1 = universeY1 +1;
            universeY2 = universeY2 +1;
            if(universeY2 >=0){
                universeY1 = 0;
                universeY2 = img3.getHeight(null)*-1;
            }
        }

    }
}
