package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyManager {
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final Random random = new Random();
    private int spawnCooldown = 0;

    public void update() {
        spawnCooldown--;
        if (spawnCooldown <= 0) {
            spawnEnemy();
            spawnCooldown = random.nextInt(60) + 30; // 次の敵を30～90フレーム後に生成
        }

        enemies.removeIf(enemy -> {
            enemy.update();
            return !enemy.isActive(); // 非アクティブな敵を削除
        });
    }

    public void draw(Graphics g) {
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    private void spawnEnemy() {
        int x = random.nextInt(Player.WINDOW_WIDTH - 50); // 画面幅内でランダム位置
        enemies.add(new Enemy(x, -50, 4, 50, 50)); // 敵を生成
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}