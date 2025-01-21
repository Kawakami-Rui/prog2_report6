package jp.ac.uryukyu.ie.e245706;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteManager {
    private BufferedImage straight, left, right;

    public SpriteManager() {
        try {
            straight = ImageIO.read(getClass().getResource("/straight.png"));
            left = ImageIO.read(getClass().getResource("/left.png"));
            right = ImageIO.read(getClass().getResource("/right.png"));
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
}