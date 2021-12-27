package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy {
    public int speed = 1;
    public int x;
    public int y;
    public  boolean hit = false;
    Image img;
    int dx;
    long timeSpent;
    boolean drift;
    boolean driftMove;
    int range = 20;
    int xLeft;
    int xRight;
    int dy = 0;
    int dyR=0;
    int health = 60;
    int randomShooting = 1;
    Random random = new Random();

    public Enemy() {

    }

    public void update(){

    }
    public void paint (Graphics graphics){
        graphics.drawImage(img,x,y,img.getWidth(null),img.getHeight(null),null);

        //graphics.fillRect(getRectangle().x,getRectangle().y,getRectangle().width,getRectangle().height);
    }
    public Rectangle getRectangle(){
        return null;
    }
    public boolean getHit(){
        return hit;
    }
    public void setHit(boolean b){
        hit = b;
    }
    public void attack(int damade) {
        health = health - damade;
        if (health<=0){
            hit = true;
        }
    }
    public int getX()
    {
        return x;
    }
    public int getY(){
        return y;
    }
}
