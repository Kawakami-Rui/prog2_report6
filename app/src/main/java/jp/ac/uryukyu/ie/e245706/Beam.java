package jp.ac.uryukyu.ie.e245706;

import java.awt.*;

public class Beam extends Bullet {

    public Beam(int x, int y) {
        super(x, y, 10, 5, 10); // スーパークラスのコンストラクタを呼び出す
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE); // ビーム専用の色
        g.fillRect(x, y, width, height);
    }
}