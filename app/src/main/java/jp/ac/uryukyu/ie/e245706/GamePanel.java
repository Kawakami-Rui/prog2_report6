package jp.ac.uryukyu.ie.e245706;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        player = new Player(400, 500);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e, bullets);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });

        timer = new Timer(16, this); // 約60 FPS
    }

    public void startGame() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        for (Bullet bullet : bullets) {
            bullet.update();
        }
        for (Enemy enemy : enemies) {
            enemy.update();
        }

        // 敵や弾の当たり判定、敵の生成などをここで処理

        repaint();
    }
}