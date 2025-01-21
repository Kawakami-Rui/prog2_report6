package jp.ac.uryukyu.ie.e245706;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Improved Shooting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Player.WINDOW_WIDTH, Player.WINDOW_HEIGHT);

        GamePanel panel = new GamePanel();
        frame.add(panel);

        frame.setVisible(true);

        panel.startGame();
    }
}