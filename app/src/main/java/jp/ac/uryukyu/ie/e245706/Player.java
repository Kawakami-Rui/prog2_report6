package jp.ac.uryukyu.ie.e245706;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player {
    private int x, y;
    private boolean left, right;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (left) x -= 5;
        if (right) x += 5;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 10);
    }

    public void keyPressed(KeyEvent e, ArrayList<Bullet> bullets) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) left = true;
        if (key == KeyEvent.VK_RIGHT) right = true;
        if (key == KeyEvent.VK_SPACE) {
            bullets.add(new Bullet(x + 25, y - 10));
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) left = false;
        if (key == KeyEvent.VK_RIGHT) right = false;
    }
}