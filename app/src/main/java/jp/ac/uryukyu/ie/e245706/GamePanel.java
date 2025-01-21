package jp.ac.uryukyu.ie.e245706;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private final Player player;

    public GamePanel() {
        this.player = new Player(Player.WINDOW_WIDTH / 2, Player.WINDOW_HEIGHT - 100);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g); // プレイヤーを描画
    }

    public void startGame() {
        Timer timer = new Timer(16, e -> {
            player.update(); // プレイヤーを更新
            repaint(); // 描画を更新
        });
        timer.start();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}