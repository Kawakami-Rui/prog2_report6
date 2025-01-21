package jp.ac.uryukyu.ie.e245706;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private final Player player; // プレイヤーを管理するフィールドを追加
    private final EnemyManager enemyManager; // 敵を管理するフィールドを追加
    private final SpriteManager spriteManager; // スプライト管理を追加

    public GamePanel() {
        this.spriteManager = new SpriteManager(); // SpriteManager を生成
        this.player = new Player(Player.WINDOW_WIDTH / 2, Player.WINDOW_HEIGHT - 100);
        this.enemyManager = new EnemyManager(spriteManager); // EnemyManager に SpriteManager を渡す

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g); // プレイヤーを描画
        enemyManager.draw(g); // 敵を描画
    }

    public void startGame() {
        Timer timer = new Timer(16, e -> {
            enemyManager.update(player); // 敵を更新
            player.update(enemyManager.getEnemies()); // 敵リストを渡してプレイヤーを更新
    
            // プレイヤーと敵のビームの衝突判定
            for (Enemy enemy : enemyManager.getEnemies()) {
                for (EnemyBullet bullet : enemy.getBullets()) {
                    if (bullet.getBounds().intersects(player.getBounds())) {
                        // ゲーム終了処理
                        System.out.println("Game Over! Player hit by enemy bullet.");
                        ((Timer) e.getSource()).stop(); // ゲームループ停止
                        return;
                    }
                }
    
                // プレイヤーが敵と衝突
                if (enemy.getBounds().intersects(player.getBounds())) {
                    System.out.println("Game Over! Player hit by enemy.");
                    ((Timer) e.getSource()).stop();
                    return;
                }
            }
    
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