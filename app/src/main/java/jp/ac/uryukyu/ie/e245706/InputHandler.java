package jp.ac.uryukyu.ie.e245706;

public class InputHandler {
    private boolean up, down, left, right, firing;

    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case java.awt.event.KeyEvent.VK_W, java.awt.event.KeyEvent.VK_UP -> up = true;
            case java.awt.event.KeyEvent.VK_S, java.awt.event.KeyEvent.VK_DOWN -> down = true;
            case java.awt.event.KeyEvent.VK_A, java.awt.event.KeyEvent.VK_LEFT -> left = true;
            case java.awt.event.KeyEvent.VK_D, java.awt.event.KeyEvent.VK_RIGHT -> right = true;
            case java.awt.event.KeyEvent.VK_SPACE -> firing = true;
        }
    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case java.awt.event.KeyEvent.VK_W, java.awt.event.KeyEvent.VK_UP -> up = false;
            case java.awt.event.KeyEvent.VK_S, java.awt.event.KeyEvent.VK_DOWN -> down = false;
            case java.awt.event.KeyEvent.VK_A, java.awt.event.KeyEvent.VK_LEFT -> left = false;
            case java.awt.event.KeyEvent.VK_D, java.awt.event.KeyEvent.VK_RIGHT -> right = false;
            case java.awt.event.KeyEvent.VK_SPACE -> firing = false;
        }
    }

    public boolean isUp() { return up; }
    public boolean isDown() { return down; }
    public boolean isLeft() { return left; }
    public boolean isRight() { return right; }
    public boolean isFiring() { return firing; }

    public boolean isMoving() {
        return up || down || left || right;
    }

    public void reset() {
        // すべてのキー入力状態をリセット
        up = false;
        down = false;
        left = false;
        right = false;
        firing = false;
    }
}