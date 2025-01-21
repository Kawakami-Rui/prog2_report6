package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    private int x, y; // プレイヤーの位置
    private int speed = 6; // 移動速度
    private boolean up, down, left, right; // 移動フラグ
    private BufferedImage sprite; //現在の画像
    private BufferedImage spriteStraight, spriteLeft, spriteRight;
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    
        // ピクセルアートの画像を読み込む
        try {
            spriteStraight = ImageIO.read(getClass().getResource("/straight.PNG"));
            spriteLeft = ImageIO.read(getClass().getResource("/left.PNG"));
            spriteRight = ImageIO.read(getClass().getResource("/right.PNG"));
            sprite = spriteStraight; // 初期状態は直進画像
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load images. Using default placeholder.");
            // デフォルトの四角形描画にフォールバック
            spriteStraight = null;
            spriteLeft = null;
            spriteRight = null;
            sprite = null;
        }
    }

    // プレイヤーの動きを更新
    public void update() {
        if (up) y -= speed;
        if (down) y += speed;
        if (left) x -= speed;
        if (right) x += speed;

        // 画面外に出ないように制限 (例: 500x900のウィンドウ)
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 500 - 32) x = 500 - 32; // プレイヤーの幅を考慮
        if (y > 900 - 32) y = 900 - 32; // プレイヤーの高さを考慮
    }

    // プレイヤーを描画
    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y,64,64, null);
        } else {
            // 画像が読み込めなかった場合、デフォルトの四角形を描画
            g.setColor(Color.BLUE);
            g.fillRect(x, y, 50, 50);
        }
    }

    // キーが押されたときの動作（共通化）
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> up = true;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = true;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                left = true;
                sprite = spriteLeft; // 左移動時の画像に変更
            }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                right = true;
                sprite = spriteRight; // 右移動時の画像に変更
            }
        }
    }

    // キーが離されたときの動作（共通化）
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> down = false;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                left = false;
                if (!right) sprite = spriteStraight; // 他のキーが押されていなければ直進画像に戻す
            }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                right = false;
                if (!left) sprite = spriteStraight; // 他のキーが押されていなければ直進画像に戻す
            }
        }
    }
}