package jp.ac.uryukyu.ie.e245706;

import java.awt.*;



public class EnemyBullet {
    private double x, y; // ビームの現在位置
    private boolean active = true;
    private double speedX, speedY; // X軸、Y軸の速度
    private final int radius = 10; // 円の半径

    public EnemyBullet(int startX, int startY, int targetX, int targetY, int speed) {
        this.x = startX;
        this.y = startY;

        // プレイヤーへの方向を計算
        double deltaX = targetX - startX;
        double deltaY = targetY - startY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // X, Y方向の速度を計算（正規化してspeedを乗じる）
        double scaleFactor = 0.1; 
        this.speedX = (deltaX / distance) * speed * scaleFactor;
        this.speedY = (deltaY / distance) * speed * scaleFactor;
    }

    public void update() {
        x += speedX; // X方向の速度を加算
        y += speedY; // Y方向の速度を加算

        if (x < 0 || x > Player.WINDOW_WIDTH || y > Player.WINDOW_HEIGHT) { // 画面外に出たら非アクティブ
            active = false;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED); // 敵ビームの色
        g.fillOval((int) x - radius, (int) y - radius, radius * 2, radius * 2); // 円を描画
    }

    public boolean isActive() {
        return active;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x,(int) y, 5, 15);
    }
}