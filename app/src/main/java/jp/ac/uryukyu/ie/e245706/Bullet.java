package jp.ac.uryukyu.ie.e245706;

import java.awt.*;

public class Bullet {
    protected int x, y; // 発射物の位置
    protected int speed; // 発射物の速度
    protected int width, height; // 発射物のサイズ
    protected boolean active = true; // 発射物が画面内にあるかどうか

    public Bullet(int x, int y, int speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    // 発射物の動きを更新
    public void update() {
        y -= speed; // 上方向に移動
        if (y < 0) { // 画面外に出たら非アクティブに
            active = false;
        }
    }

    // 発射物を描画
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    // 発射物がアクティブかどうかを返す
    public boolean isActive() {
        return active;
    }
}