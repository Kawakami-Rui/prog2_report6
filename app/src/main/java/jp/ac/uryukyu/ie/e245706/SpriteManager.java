package jp.ac.uryukyu.ie.e245706;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteManager {
    private BufferedImage straight, left, right, enemy; // 敵画像用フィールドを追加

    public SpriteManager() {
        try {
            // プレイヤーの画像をロード
            straight = ImageIO.read(getClass().getResource("/straight.png"));
            left = ImageIO.read(getClass().getResource("/left.png"));
            right = ImageIO.read(getClass().getResource("/right.png"));

            // 敵の画像をロード
            enemy = ImageIO.read(getClass().getResource("/enemy.png")); // 画像ファイルパスを指定
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load images. Check file paths.");
            System.exit(1);
        }
    }

    public BufferedImage getStraight() {
        return straight;
    }

    public BufferedImage getLeft() {
        return left;
    }

    public BufferedImage getRight() {
        return right;
    }

    public BufferedImage getEnemy() {
        return enemy;
    }
}