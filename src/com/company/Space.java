package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by admin on 20.05.2020.
 */
public class Space extends JPanel  implements ActionListener{
    BackGround backGround = new BackGround();
    Timer t = new Timer(5,this);
    static ArrayList<TestBax> testBaxes = new ArrayList<>();
    static ArrayList<Boss> bosses = new ArrayList<>();
    Hero hero = new Hero();
    static ArrayList<Snarryad> snarryads = new ArrayList<>();
    static ArrayList<SnEn> snarryadEnemy = new ArrayList<>();
    static ArrayList<Enemy> enemies = new ArrayList<>();
    DrawThread drawThread = new DrawThread();
    static boolean wictory = false;
    Random random = new Random();
    int mode = 0;

    public Space(){
        addKeyListener(new Control(hero));
        t.start();
        drawThread.start();
        setFocusable(true);

        for(int i = 0;i<20           ;i++){
             int r = random.nextInt(3);
            int x=random.nextInt(1000);
            int y=random.nextInt(500);
            if(r==0){
                enemies.add(new EnemyBlue(x,y));
            }else if(r==1){
                enemies.add(new EnemyBrown(x,y));
            }else if(r==2){
                enemies.add(new EnemyDark(x,y));
            }
        }
        bosses.add(new Boss());

    }
    public void update(){
        backGround.update();
        hero.update();
        for(int i = 0;i<bosses.size();i++){
            bosses.get(i).update();
            if(bosses.get(i).delete==true){
                bosses.remove(i);
            }
        }
        for (int i=0; i <snarryads.size();i++){
            snarryads.get(i).update();
            if(snarryads.get(i).y<-50){
                snarryads.remove(i);
                i--;
            }
        }
        for (int i=0; i <snarryadEnemy.size();i++){
            snarryadEnemy.get(i).update();
            if(snarryadEnemy.get(i).getRectangle().intersects(hero.getRectangle())){
                hero.setStBlink(true);
                hero.setDamage(snarryadEnemy.get(i).getDamage());
                snarryadEnemy.remove(i);
            }
        }
        //snarryadsRemove();
        if(mode == 0) {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).update();
                for (int j = 0; j < snarryads.size(); j++) {
                    if (enemies.get(i).getRectangle().intersects(snarryads.get(j).getRectangle())) {
                        enemies.get(i).attack(snarryads.get(j).damage);
                        snarryads.remove(j);
                    }
                }
            }
            System.out.println(enemies.size());
            if (enemies.size() == 0) {
                mode = 1;
                if(bosses.size()!=0) {
                    bosses.get(0).stop = false;
                }
            }
        }
        for (int j=0;j<snarryads.size();j++){
            for(int i = 0;i<bosses.size();i++){
                if(bosses.get(i).getRectangle().intersects(snarryads.get(j).getRectangle())){
                    bosses.get(i).setFool(true);
                    if(bosses.get(i).startBreak==false) {
                        snarryads.remove(j);
                    }
                    break;
                }
            }
        }
        rivalRemove();
        for (int i =0; i <testBaxes.size();i++){
            testBaxes.get(i).update();
        }
        testBaxesremove();
        if(hero.restart==true){
            restart();
            hero.restart=false;
            hero.run = true;
            hero.armor = hero.maxArmor;
            hero.life = 200;
        }
    }
    public void paint (Graphics graphics){
        backGround.paint(graphics);
        for(int i = 0;i<bosses.size();i++){
            bosses.get(i).paint(graphics);
        }
        for (int i=0; i <snarryads.size();i++){
            snarryads.get(i).paint(graphics);
        }
        for(int i = 0;i<enemies.size();i++){
            enemies.get(i).paint(graphics);
            //graphics.fillRect(enemies.get(i).getRectangle().x,enemies.get(i).getRectangle().y,enemies.get(i).getRectangle().width,enemies.get(i).getRectangle().height);
        }
        for (int i=0; i <snarryadEnemy.size();i++){
            snarryadEnemy.get(i).paint(graphics);
        }
        hero.paint(graphics);
        //graphics.fillRect(hero.getRectangle().x,hero.getRectangle().y,hero.getRectangle().width,hero.getRectangle().height);
        for (int i =0; i <testBaxes.size();i++){
            testBaxes.get(i).paint(graphics);
        }
        Graphics2D g2d = (Graphics2D)graphics.create();
        AlphaComposite composite = AlphaComposite.SrcOver.derive( 0.5f );
        g2d.setComposite( composite );
        //g2d.drawImage(img3,400,500,img3.getWidth(null),img3.getHeight(null),null);

        //graphics.fillRect(boss.getRectangle().x,boss.getRectangle().y,boss.getRectangle().width,boss.getRectangle().height);
        if(wictory == true){
            Font f1 = new Font("TimesRoman", Font.BOLD, 42);
            graphics.setFont(f1);
            graphics.setColor(color(34, 227, 9));
            graphics.drawString("Победа !!! ",430,440);
            Font f2 = new Font("TimesRoman", Font.BOLD, 25);
            graphics.setFont(f2);
            graphics.setColor(color(224, 227, 229));
            graphics.drawString("Чтобы перезапустить игру нажмите клавишу R ",230,840);
        }
        if(hero.run == false){
            Font f1 = new Font("TimesRoman", Font.BOLD, 42);
            graphics.setFont(f1);
            graphics.setColor(color(224, 27, 9));
            graphics.drawString("Поражение ",430,440);
            Font f2 = new Font("TimesRoman", Font.BOLD, 25);
            graphics.setFont(f2);
            graphics.setColor(color(224, 227, 229));
            graphics.drawString("Чтобы перезапустить игру нажмите клавишу R ",230,840);

        }
    }
    public Color color(int red, int green, int blue){//метод установки цвета
        return new Color(red,green,blue);
    }
    private void snarryadsRemove()
    {
        boolean flag = false;
        for(int i =0 ; i < snarryads.size();i++){
            if(snarryads.get(i).y<-50){
                snarryads.remove(i);
                flag = true;
                break;
            }
        }
        if(flag == true)
        {
            //snarryadsRemove();
        }
    }
    public void actionPerformed(ActionEvent e) {
        update();
    }
    public class DrawThread extends Thread{
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void rivalRemove()
    {
        for(int i =0 ; i < enemies.size();i++){
            if(enemies.get(i).getHit()==true){
                testBaxes.add(new TestBax(enemies.get(i).getX(),enemies.get(i).getY()));
                enemies.remove(i);
                i--;
            }
        }
    }
    private void testBaxesremove()
    {
        boolean flag = false;
        for(int i =0 ; i < testBaxes.size();i++){
            if(testBaxes.get(i).delete==true){
                testBaxes.remove(i);
                flag = true;
                break;
            }
        }
        if(flag == true)
        {
            testBaxesremove();
        }
    }

    private  void restart(){
        testBaxes = new ArrayList<>();
        bosses = new ArrayList<>();
        //hero = new Hero();
        snarryads = new ArrayList<>();
        snarryadEnemy = new ArrayList<>();
        enemies = new ArrayList<>();
        wictory = false;
        mode = 0;
        for(int i = 0;i<20;i++){
            int r = random.nextInt(3);
            int x=random.nextInt(1000);
            int y=random.nextInt(500);
            if(r==0){
                enemies.add(new EnemyBlue(x,y));
            }else if(r==1){
                enemies.add(new EnemyBrown(x,y));
            }else if(r==2){
                enemies.add(new EnemyDark(x,y));
            }
        }
        bosses.add(new Boss());

    }
}
