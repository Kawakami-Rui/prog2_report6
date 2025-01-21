package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    private int x, y; // プレイヤーの位置
    private int speed = 6; // 移動速度
    private boolean up, down, left, right; // 移動フラグ
    private BufferedImage sprite; // 現在の画像
    private final SpriteManager spriteManager;

    // ウィンドウサイズの定数
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 900;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;

        // SpriteManagerで画像を管理
        spriteManager = new SpriteManager();

        // 初期状態は直進画像
        sprite = spriteManager.getStraight();
        if (sprite == null) {
            System.err.println("Warning: Default sprite (straight) is null. Using placeholder.");
        }
    }

// プレイヤーの動きを更新
public void update() {
    boolean moved = false; // 動きが発生したかどうかのフラグ

    double diagonalSpeed = speed / Math.sqrt(2); // 斜め移動時の速度

    if (up && left) {
        y -= diagonalSpeed;
        x -= diagonalSpeed;
        moved = true;
    } else if (up && right) {
        y -= diagonalSpeed;
        x += diagonalSpeed;
        moved = true;
    } else if (down && left) {
        y += diagonalSpeed;
        x -= diagonalSpeed;
        moved = true;
    } else if (down && right) {
        y += diagonalSpeed;
        x += diagonalSpeed;
        moved = true;
    } else {
        if (up) {
            y -= speed;
            moved = true;
        }
        if (down) {
            y += speed;
            moved = true;
        }
        if (left) {
            x -= speed;
            moved = true;
        }
        if (right) {
            x += speed;
            moved = true;
        }
    }

    // 画面外に出ないように制限
    if (x < 0) x = 0;
    if (y < 0) y = 0;
    if (x > WINDOW_WIDTH - 64) x = WINDOW_WIDTH - 64; // プレイヤーの幅を考慮
    if (y > WINDOW_HEIGHT - 64) y = WINDOW_HEIGHT - 64; // プレイヤーの高さを考慮

    // 画像の切り替え（移動中または静止中の場合）
    if (moved) {
        if (left && right) {
            sprite = spriteManager.getStraight(); // 左右同時押しなら直進画像
        } else if (left) {
            sprite = spriteManager.getLeft(); // 左移動中
        } else if (right) {
            sprite = spriteManager.getRight(); // 右移動中
        } else {
            sprite = spriteManager.getStraight(); // 他の場合は直進画像
        }
    } else {
        // 移動していない場合、直進画像に戻す
        sprite = spriteManager.getStraight();
    }
}

    // プレイヤーを描画
    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, 64, 64, null);
        } else {
            System.err.println("Warning: sprite is null. Drawing placeholder.");
            g.setColor(Color.BLUE);
            g.fillRect(x, y, 50, 50);
        }
    }

    // キーが押されたときの動作
    public void keyPressed(java.awt.event.KeyEvent e) {
        int key = e.getKeyCode();

        if (key == java.awt.event.KeyEvent.VK_W || key == java.awt.event.KeyEvent.VK_UP) up = true;
        if (key == java.awt.event.KeyEvent.VK_S || key == java.awt.event.KeyEvent.VK_DOWN) down = true;
        if (key == java.awt.event.KeyEvent.VK_A || key == java.awt.event.KeyEvent.VK_LEFT) left = true;
        if (key == java.awt.event.KeyEvent.VK_D || key == java.awt.event.KeyEvent.VK_RIGHT) right = true;
    }

    // キーが離されたときの動作
    public void keyReleased(java.awt.event.KeyEvent e) {
        int key = e.getKeyCode();

        if (key == java.awt.event.KeyEvent.VK_W || key == java.awt.event.KeyEvent.VK_UP) up = false;
        if (key == java.awt.event.KeyEvent.VK_S || key == java.awt.event.KeyEvent.VK_DOWN) down = false;
        if (key == java.awt.event.KeyEvent.VK_A || key == java.awt.event.KeyEvent.VK_LEFT) left = false;
        if (key == java.awt.event.KeyEvent.VK_D || key == java.awt.event.KeyEvent.VK_RIGHT) right = false;
    }
}