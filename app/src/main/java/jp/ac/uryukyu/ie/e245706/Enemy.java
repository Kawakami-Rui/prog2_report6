package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Enemy {
    private int x, y; // 敵の位置
    private int speed; // 敵の移動速度
    private int width, height; // 敵のサイズ
    private boolean active = true; // 敵が画面内にいるかどうか
    private final EnemyBulletManager bulletManager = new EnemyBulletManager(); // 敵のビーム管理
    private BufferedImage sprite; // 敵の画像


    public Enemy(int x, int y, int speed, int width, int height ,SpriteManager spriteManager) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.sprite = spriteManager.getEnemy();
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
        if (sprite != null) {
            g.drawImage(sprite, x, y, width, height,null); // 画像を描画
        } else {
            g.setColor(Color.RED); // 画像がない場合の代替描画
            g.fillRect(x, y, 50, 50);
        }
    }

    public boolean isActive() {
        return active;
    }


    public Rectangle getBounds() {
        if (sprite != null) {
            return new Rectangle(x, y, sprite.getWidth() / 2, sprite.getHeight() / 2); // 画像サイズに基づく矩形
        } else {
            return new Rectangle(x, y, 50, 50); // デフォルトの矩形
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

        public ArrayList<EnemyBullet> getBullets() {
        return bulletManager.getBullets();
    }
}