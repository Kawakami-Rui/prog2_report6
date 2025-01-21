package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.util.ArrayList;

public class EnemyBulletManager {
    private final ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();

    public void fire(int startX, int startY, int targetX, int targetY) {
        int fixedSpeed = 5; // 固定速度を設定
        enemyBullets.add(new EnemyBullet(startX, startY, targetX, targetY,fixedSpeed)); // 敵のビームを生成
    }

    public void update() {
        enemyBullets.removeIf(bullet -> {
            bullet.update();
            return !bullet.isActive(); // 非アクティブなビームを削除
        });
    }

    public void draw(Graphics g) {
        for (EnemyBullet bullet : enemyBullets) {
            bullet.draw(g);
        }
    }

    public ArrayList<EnemyBullet> getBullets() {
        return enemyBullets;
    }
}