package com.company;

import javax.swing.*;

public class Main {
    private static JFrame jFrame;//обявляем обект JFrame
    private static final int WIDTH = 1000;// ширина
    private static final int HEIGHT = 1000;// высота
    public static void main(String[] args) {
        jFrame =  new JFrame("Space Shooter");//установить  название окна
        jFrame.setSize(WIDTH,HEIGHT);//размеры окна
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//выход
        jFrame.setResizable(false);//запрет изменение размеров окна
        jFrame.setLocationRelativeTo(null);// разместить по центру
        jFrame.add(new Space());
        jFrame.setVisible(true);//сделать видимым
    }
}


