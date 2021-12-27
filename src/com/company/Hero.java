package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

public class Hero {
    Random random = new Random();
    public int x = 600;
    public int y = 800;
    public int speed = 0;
    Image img = new ImageIcon("res/hero1.png").getImage();
    Image img3= new ImageIcon("res/e2.png").getImage();
    int dx = img.getWidth(null)/2;
    float alfa = 0.2f;
    long startTime = System.currentTimeMillis();
    long timeSpent;
    long timeShield;
    int d =0;
    boolean shooting = true;
    boolean alft = true;
    boolean stBlink = false;
    public  int life = 200;
    public int  armor = 200;
    public int  maxArmor = 200;
    public  int energy_shield = 100;
    Health health = new Health();
    public boolean run = true;
    public boolean restart = false;
    public void update(){
        if (run) {
            x += speed;
            if (x <= 0) {
                x = 0;
            }
            if (x >= 945) {
                x = 945;
            }
            if ((System.currentTimeMillis() - timeSpent) > 200) {
                shooting = true;
            }
            if (stBlink) {
                blink();
            }
            shieldRegeneration();
        }
            health.life = life;
            health.armor = armor;


    }
    public void paint (Graphics graphics){
        if (run) {
            graphics.drawImage(img, x, y, img.getWidth(null), img.getHeight(null), null);
            Graphics2D g2d = (Graphics2D) graphics.create();
            AlphaComposite composite = AlphaComposite.SrcOver.derive(alfa);
            g2d.setComposite(composite);
            //g2d.drawImage(img3,400,500,img3.getWidth(null),img3.getHeight(null),null);
            g2d.drawImage(img3, x - 33, y - 15, img3.getWidth(null), img3.getHeight(null), null);
        }
        health.paint(graphics);
    }
    public void  keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (run) {

            if (key == KeyEvent.VK_RIGHT) {
                    speed = 2;
            }
            if  (key == KeyEvent.VK_LEFT) {
                 speed = -2;
             }
            if (key == KeyEvent.VK_SPACE)      {
                if (shooting) {
                    Space.snarryads.add(new Snarryad(x + dx, y));
                    shooting = false;
                    timeSpent = System.currentTimeMillis();
                }
            }
        }else {
            if (key == KeyEvent.VK_R) {
                restart = true;
            }
        }
        if (Space.wictory==true){
            if (key == KeyEvent.VK_R) {
                restart = true;
            }
        }

    }
    public void  keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT)
        {
            speed = 0;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            speed = 0;
        }
        if(key == KeyEvent.VK_UP)
        {
        }
    }
    public Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle(x+5,y+5,img.getWidth(null)-10,img.getHeight(null)-10);
        return rectangle;
    }
    public void blink(){
        if (armor>0) {
            d++;
            if (d == 1) {
                if (alft == true) {
                    alfa = alfa + 0.02f;
                    if (alfa >= 1f) {
                        alfa = 1f;
                        alft = false;
                    }
                } else {
                    alfa = alfa - 0.01f;
                    if (alfa <= 0.2f) {
                        alfa = 0.2f;
                        alft = true;
                        stBlink = false;
                    }

                }
                d = 0;
            }
        }
    }

    public void setStBlink(boolean stBlink) {
        if (this.stBlink==true){
            alft = true;
        }
        this.stBlink = stBlink;
        if (armor<=0){
            alfa = 0.0f;
        }
    }
    public  void  setDamage(int d){
        if (run) {
            armor = armor - d;
            if (armor <= 0) {
                armor = 0;
            }
            int rd = random.nextInt(maxArmor);
            if (rd > armor) {
                life = life - d;
                if (life <= 0) {
                    life = 0;
                    run = false;
                    Space.testBaxes.add(new TestBax(x, y));
                }
            }
        }
    }

    public void shieldRegeneration(){
        if ((System.currentTimeMillis()-timeShield)>600){
            armor = armor+1;
            if (armor>=maxArmor){
                armor = maxArmor;
            }
            timeShield = System.currentTimeMillis();
        }
    }
}
