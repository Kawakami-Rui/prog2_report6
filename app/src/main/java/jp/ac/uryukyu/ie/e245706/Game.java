package jp.ac.uryukyu.ie.e245706;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Shooting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        GamePanel panel = new GamePanel();
        frame.add(panel);

        frame.setVisible(true);

        panel.startGame();
    }
}