package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.util.ArrayList;

public class BulletManager {
    private final ArrayList<Bullet> bullets = new ArrayList<>();

    // ビームを発射
    public void fire(int x, int y) {
        bullets.add(new Beam(x, y));
    }

    // ビームを更新
    public void update() {
        bullets.removeIf(bullet -> {
            bullet.update();
            return !bullet.isActive(); // 非アクティブな発射物を削除
        });
    }

    // ビームを描画
    public void draw(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }
}