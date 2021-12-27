package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by admin on 04.06.2020.
 */
public class Boss {
    ArrayList<BossBax>bossBaxes = new ArrayList<>();
    ArrayList<BossWreckage>bossWreckages = new ArrayList<>();
    public int x = 200;
    public int y = -700;
    ArrayList<BossComponent> bossComponents = new ArrayList<>();
    public int speed = 1;
    //Image img = new ImageIcon("res/boss.png").getImage();
    Image img3= new ImageIcon("res/boss1png.png").getImage();
    //int dx = img.getWidth(null)/2;
    Random random = new Random();
    float alfa = 0.2f;
    long startTime = System.currentTimeMillis();
    long timeSpent = 0;
    long timeSpent1 = 0;
    int d =0;
    boolean shooting = true;
    boolean roz = true;
    boolean stBlink = false;
    int frame = 0;
    boolean fool = false;

    boolean drift;
    boolean driftMove;
    int range = 500;
    int xLeft = x - range;
    int xRight = x + range;
    int health = 20;
    int randomShooting = 1;
    int b = 50;
    boolean deadBoss = false;
    boolean startBreak = false;
    boolean delete = false;
    int mode = 0;
    boolean stop = true;
    public  Boss(){
        bossComponents.add(new BossComponent(x,y,5));
        bossComponents.add(new BossComponent(x,y,10));
        bossComponents.add(new BossComponent(x,y,15));
        bossComponents.add(new BossComponent(x,y,20));
        bossComponents.add(new BossComponent(x,y,25));
        bossComponents.add(new BossComponent(x,y,30));
        bossComponents.add(new BossComponent(x,y,35));
        bossComponents.add(new BossComponent(x,y,40));
    }
    public void update(){
        if(stop==false) {
            if (startBreak == false) {
                if (deadBoss == false) {
                    if (mode == 1) {
                        if (driftMove) {
                            if (drift) {
                                x = x - speed;
                            } else {
                                x = x + speed;
                            }
                            if (x < xLeft || x > xRight) {
                                drift = !drift;
                                driftMove = false;
                                timeSpent1 = System.currentTimeMillis();
                            }
                        } else {
                            if ((System.currentTimeMillis() - timeSpent1) > 1000) {
                                driftMove = true;
                            }
                        }
                        if ((System.currentTimeMillis() - timeSpent) > 600) {
                            shooting = true;
                            timeSpent = System.currentTimeMillis();
                            if (roz == true) {
                                d++;
                                if (d > 4) {
                                    d = 3;
                                    roz = false;
                                }
                            } else {
                                d--;
                                if (d < 0) {
                                    d = 1;
                                    roz = true;
                                }

                            }
                        }
                    }
                    if (mode == 0) {
                        y += 1;
                        if (y >= 0) {
                            y = 0;
                            mode = 1;
                        }
                    }
                    for (int i = 0; i < bossComponents.size(); i++) {
                        bossComponents.get(i).update(x, y, d);
                        if (bossComponents.get(i).y > 2000) {
                            bossComponents.remove(i);
                        }
                    }
                    if (fool == true) {
                        if (bossComponents.size() > 0) {
                            int r = random.nextInt(bossComponents.size());
                            int dframe = bossComponents.get(r).frame;
                            int TBx = 127;
                            int TBy = 330;
                            switch (dframe) {
                                case 5:
                                    TBx = x + 127;
                                    TBy = y + 330;
                                    break;
                                case 10:
                                    TBx = x + 320;
                                    TBy = y + 330;
                                    break;
                                case 15:
                                    TBx = x + 90;
                                    TBy = y + 310;
                                    break;
                                case 20:
                                    TBx = x + 390;
                                    TBy = y + 310;
                                    break;
                                case 25:
                                    TBx = x + 155;
                                    TBy = y + 165;
                                    break;
                                case 30:
                                    TBx = x + 340;
                                    TBy = y + 165;
                                    break;
                                case 35:
                                    TBx = x + 110;
                                    TBy = y + 60;
                                    break;
                                case 40:
                                    TBx = x + 350;
                                    TBy = y + 60;
                                    break;
                            }
                            Space.testBaxes.add(new TestBax(TBx, TBy));
                            bossComponents.get(r).mode = 2;
                        } else {
                            int r = random.nextInt(3);
                            int rX = random.nextInt(50);
                            switch (r) {
                                case 0:
                                    Space.testBaxes.add(new TestBax(x + 195 + rX, y + 100));
                                    break;
                                case 1:
                                    Space.testBaxes.add(new TestBax(x + 195 + rX, y + 200));
                                    break;
                                case 2:
                                    Space.testBaxes.add(new TestBax(x + 195 + rX, y + 300));
                                    break;
                            }
                            health--;
                        }
                        fool = false;
                    }
                    randomShooting = random.nextInt(100);
                    if (randomShooting == 0) {
                        Space.snarryadEnemy.add(new SnarryadEnemy2(x + 270, y + 300));
                        randomShooting = 1;
                    }
                    if (health <= 0) {
                        deadBoss = true;
                    }
                }
                if (deadBoss == true) {
                    int r = random.nextInt(b);
                    if (r == 0) {
                        bossBaxes.add(new BossBax(random.nextInt(200) + x + 100, random.nextInt(200) + y + 100));
                        b--;
                        if (b <= 8) {
                            b = 8;
                            int s = random.nextInt(10);
                            if (s == 1) {
                                startBreak = true;
                                bossWreckages.add(new BossWreckage(x, y, 0));
                                bossWreckages.add(new BossWreckage(x, y, 1));
                                bossWreckages.add(new BossWreckage(x, y, 2));
                                bossWreckages.add(new BossWreckage(x, y, 3));
                                Space.wictory = true;
                            }
                        }
                    }
                }
            }else {
                for (int i = 0; i < bossWreckages.size(); i++) {
                    bossWreckages.get(i).update();
                    if (bossWreckages.get(i).x > 1500 || bossWreckages.get(i).x < -500) {
                        bossWreckages.remove(i);
                        break;
                    }
                }
                if (bossWreckages.size() == 0) {
                    delete = true;
                }
            }
            for (int i = 0; i < bossBaxes.size(); i++) {
                bossBaxes.get(i).update();
            }
            bossBaxesremove();
        }
    }
    public void paint (Graphics graphics){
        if (startBreak == false) {
            drawSpriteFrame(img3, graphics, x, y, 9, 0, 529, 556);
            for (int i = 0; i < bossComponents.size(); i++) {
                bossComponents.get(i).paint(graphics);
            }
        }else {
            for (int i = 0; i < bossWreckages.size(); i++) {
                bossWreckages.get(i).paint(graphics);
            }
        }
        for (int i =0; i <bossBaxes.size();i++){
            bossBaxes.get(i).paint(graphics);
        }
    }
    void drawSpriteFrame(Image source, Graphics g2d, int x, int y, int columns, int frame, int width, int height)
    {
        int frameX = (frame % columns) * width;
        int frameY = (frame / columns) * height;
        g2d.drawImage(source, x, y, x+width, y+height,
                frameX, frameY, frameX+width, frameY+height, null);
    }
    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(x+150,y+100,img3.getWidth(null)/5-300,img3.getHeight(null)/9-300);
        return rectangle;
    }

    public  void  setFool(boolean b){
        if (bossComponents.size()>0) {
            int r = random.nextInt(bossComponents.size());
            if (r == 0) {
                fool = b;
            }
        }else {
            fool = b;
        }
    }
    private void bossBaxesremove()
    {
        boolean flag = false;
        for(int i =0 ; i < bossBaxes.size();i++){
            if(bossBaxes.get(i).delete==true){
                bossBaxes.remove(i);
                flag = true;
                break;
            }
        }
        if(flag == true)
        {
            bossBaxesremove();
        }
    }
}
