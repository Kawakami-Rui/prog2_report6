package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y; // プレイヤーの位置
    private int speed = 6; // 移動速度
    private BufferedImage sprite; // 現在の画像
    private final SpriteManager spriteManager; // スプライト管理
    private final BulletManager bulletManager; // ビーム管理
    private final InputHandler inputHandler; // 入力管理

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 900;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;

        // 管理クラスの初期化
        spriteManager = new SpriteManager();
        bulletManager = new BulletManager();
        inputHandler = new InputHandler();

        // 初期状態は直進画像
        sprite = spriteManager.getStraight();
        if (sprite == null) {
            System.err.println("Warning: Default sprite (straight) is null. Using placeholder.");
        }
    }

    public void update(ArrayList<Enemy> enemies) {
        updatePosition();
        restrictWithinBounds();
        updateSprite();
        bulletManager.update(enemies); // ビームの更新
    }

    public void draw(Graphics g) {
        drawPlayer(g);
        bulletManager.draw(g); // ビームの描画
    }

    public void keyPressed(KeyEvent e) {
        inputHandler.keyPressed(e.getKeyCode());
        if (inputHandler.isFiring()) {
            fireBullet();
        }
    }

    public void keyReleased(KeyEvent e) {
        inputHandler.keyReleased(e.getKeyCode());
    }

    public BulletManager getBulletManager() {
        return bulletManager;
    }

    private void updatePosition() {
        double diagonalSpeed = speed / Math.sqrt(2);

        if (inputHandler.isUp() && inputHandler.isLeft()) {
            y -= diagonalSpeed;
            x -= diagonalSpeed;
        } else if (inputHandler.isUp() && inputHandler.isRight()) {
            y -= diagonalSpeed;
            x += diagonalSpeed;
        } else if (inputHandler.isDown() && inputHandler.isLeft()) {
            y += diagonalSpeed;
            x -= diagonalSpeed;
        } else if (inputHandler.isDown() && inputHandler.isRight()) {
            y += diagonalSpeed;
            x += diagonalSpeed;
        } else {
            if (inputHandler.isUp()) y -= speed;
            if (inputHandler.isDown()) y += speed;
            if (inputHandler.isLeft()) x -= speed;
            if (inputHandler.isRight()) x += speed;
        }
    }

    private void restrictWithinBounds() {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > WINDOW_WIDTH - 64) x = WINDOW_WIDTH - 64;
        if (y > WINDOW_HEIGHT - 64) y = WINDOW_HEIGHT - 64;
    }

    private void updateSprite() {
        if (inputHandler.isMoving()) {
            if (inputHandler.isLeft() && inputHandler.isRight()) {
                sprite = spriteManager.getStraight();
            } else if (inputHandler.isLeft()) {
                sprite = spriteManager.getLeft();
            } else if (inputHandler.isRight()) {
                sprite = spriteManager.getRight();
            } else {
                sprite = spriteManager.getStraight();
            }
        } else {
            sprite = spriteManager.getStraight();
        }
    }

    private void drawPlayer(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, 64, 64, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, 50, 50);
        }
    }

    private void fireBullet() {
        bulletManager.fire(x + 32 - 2, y); // 中央からビームを発射
    }
}