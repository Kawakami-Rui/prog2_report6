package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.util.ArrayList;

public class Enemy {
    private int x, y; // 敵の位置
    private int speed; // 敵の移動速度
    private int width, height; // 敵のサイズ
    private boolean active = true; // 敵が画面内にいるかどうか
    private final EnemyBulletManager bulletManager = new EnemyBulletManager(); // 敵のビーム管理
    private final int radius = 10; // 円の半径


    public Enemy(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public void update(Player player) {
        y += speed; // 下方向に移動
        if (y > Player.WINDOW_HEIGHT) { // 画面外に出たら非アクティブ
            active = false;
        }

        // 一定確率でビームを発射
        if (Math.random() < 0.01) { // 1%の確率でビームを発射
            bulletManager.fire(x + width / 2, y + height, player.getX(), player.getY()); // プレイヤーを狙う
        }
    
        bulletManager.update();
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval((int) x - radius, (int) y - radius, radius * 2, radius * 2);
        bulletManager.draw(g); // 敵のビームを描画
    }

    public boolean isActive() {
        return active;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

        public ArrayList<EnemyBullet> getBullets() {
        return bulletManager.getBullets();
    }
}