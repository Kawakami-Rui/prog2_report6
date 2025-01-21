package jp.ac.uryukyu.ie.e245706;

import java.awt.*;

public class Bullet {
    private int x, y;
    private boolean active = true;

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        y -= 10;
        if (y < 0) active = false;
    }

    public void draw(Graphics g) {
        if (active) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 5, 10);
        }
    }

    public boolean isActive() {
        return active;
    }
}