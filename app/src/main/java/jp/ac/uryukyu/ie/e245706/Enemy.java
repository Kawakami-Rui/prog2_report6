package jp.ac.uryukyu.ie.e245706;

import java.awt.*;

public class Enemy {
    private int x, y; // 敵の位置
    private int speed; // 敵の移動速度
    private int width, height; // 敵のサイズ
    private boolean active = true; // 敵が画面内にいるかどうか

    public Enemy(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public void update() {
        y += speed; // 下方向に移動
        if (y > Player.WINDOW_HEIGHT) { // 画面外に出たら非アクティブ
            active = false;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
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
}