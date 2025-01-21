package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<EnemyBullet> enemyBullets = new ArrayList<>(); // 敵のビームを一括管理
    private final Random random = new Random();
    private int spawnCooldown = 0; // 敵生成のクールダウンタイマー

    public void update(Player player) {
        // 敵を生成する間隔を制御
        if (spawnCooldown <= 0) {
            spawnEnemy(); // 新しい敵を生成
            spawnCooldown = random.nextInt(60) + 30; // 30～90フレーム間隔で生成
        } else {
            spawnCooldown--;
        }
        // 敵を更新し、非アクティブな敵を削除
        enemies.removeIf(enemy -> {
            enemy.update(player); // プレイヤーを渡して更新
            enemyBullets.addAll(enemy.getBullets()); // 敵のビームを収集
            return !enemy.isActive();
        });

        // 敵のビームを更新
        enemyBullets.removeIf(bullet -> {
            bullet.update();
            return !bullet.isActive();
        });
    }

    public void draw(Graphics g) {
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (EnemyBullet bullet : enemyBullets) {
            bullet.draw(g);
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<EnemyBullet> getBullets() {
        return enemyBullets;
    }

    // 敵を生成するメソッド
    private void spawnEnemy() {
        int x = random.nextInt(Player.WINDOW_WIDTH - 50); // ランダムなX座標
        int speed = random.nextInt(3) + 1; // ランダムな速度（1～3）
        enemies.add(new Enemy(x, -50, speed, 50, 50)); // 新しい敵をリストに追加
    }
}