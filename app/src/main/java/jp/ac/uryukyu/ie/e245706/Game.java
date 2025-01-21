package jp.ac.uryukyu.ie.e245706;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Improved Shooting Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Player.WINDOW_WIDTH, Player.WINDOW_HEIGHT);

        GamePanel panel = new GamePanel(); // GamePanelを作成
        frame.add(panel); // フレームにパネルを追加

        frame.setVisible(true); // フレームを表示

        // `startGame()` は呼び出さず、タイトル画面からゲームを開始
        // タイトル画面の初期化は GamePanel 内で管理
    }
}