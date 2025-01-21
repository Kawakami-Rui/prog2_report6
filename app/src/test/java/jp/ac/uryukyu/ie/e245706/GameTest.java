package jp.ac.uryukyu.ie.e245706;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Player player;
    private BulletManager bulletManager;

    @BeforeEach
    void setUp() {
        player = new Player(Player.WINDOW_WIDTH / 2, Player.WINDOW_HEIGHT - 100);
        bulletManager = new BulletManager();
    }

    @Test
    void testPlayerReset() {
        // プレイヤーの位置を変更
        KeyEvent keyEvent = new KeyEvent(new JButton(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        player.keyPressed(keyEvent); // プレイヤーを上に移動
        player.update(new ArrayList<>()); // 更新
        assertNotEquals(Player.WINDOW_HEIGHT - 100, player.getBounds().y); // 移動を確認

        // リセット
        player.reset();

        // 初期位置に戻っているか確認
        assertEquals(Player.WINDOW_WIDTH / 2, player.getBounds().x);
        assertEquals(Player.WINDOW_HEIGHT - 100, player.getBounds().y);
    }

    @Test
    void testBulletManagerClear() {
        // 弾丸を発射
        bulletManager.fire(100, 200);
        bulletManager.fire(150, 250);
        assertFalse(bulletManager.getBullets().isEmpty()); // 弾丸が発射されたか確認

        // クリア
        bulletManager.clear();

        // 弾丸がすべて削除されていることを確認
        assertTrue(bulletManager.getBullets().isEmpty());
    }
}