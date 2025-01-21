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
    public void update(ArrayList<Enemy> enemies) {
        bullets.removeIf(bullet -> {
            bullet.update();
            if (!bullet.isActive()) {
                return true; // 非アクティブなビームを削除
            }

            // 衝突判定
            for (Enemy enemy : enemies) {
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bullet.setActive(false); // ビームを非アクティブに
                    enemy.setActive(false);  // 敵を非アクティブに
                    return true; // ビームを削除
                }
            }
            return false;
        });
    }
    // ビームを描画
    public void draw(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    // ビームをすべて削除
    public void clear() {
        bullets.clear();
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}