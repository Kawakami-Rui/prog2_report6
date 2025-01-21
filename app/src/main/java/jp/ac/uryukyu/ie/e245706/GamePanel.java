package jp.ac.uryukyu.ie.e245706;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements KeyListener {
    // ゲームの状態を表す列挙型
    private enum GameState { TITLE, COUNTDOWN, PLAYING, GAME_OVER }

    private GameState gameState = GameState.TITLE; // 初期状態はタイトル画面

    private final Player player; // プレイヤー
    private final EnemyManager enemyManager; // 敵の管理
    private final SpriteManager spriteManager; // スプライト管理

    private JButton startButton; // スタートボタン
    private JButton continueButton; // コンティニューボタン
    private JButton quitButton; // 終了ボタン
    private int countdown = 3; // カウントダウンの初期値

    public GamePanel() {
        // 各種マネージャーの初期化
        this.spriteManager = new SpriteManager();
        this.player = new Player(Player.WINDOW_WIDTH / 2, Player.WINDOW_HEIGHT - 100);
        this.enemyManager = new EnemyManager(spriteManager);

        // イベントリスナーの設定
        addKeyListener(this);
        setFocusable(true);

        // レイアウト設定とボタンの初期化
        setLayout(null);
        initializeButtons();
    }

    // ボタンを初期化
    private void initializeButtons() {
        // スタートボタン
        startButton = new JButton("Start Game");
        startButton.setBounds(Player.WINDOW_WIDTH / 2 - 75, Player.WINDOW_HEIGHT / 2 - 25, 150, 50);
        startButton.addActionListener(e -> startCountdown());
        add(startButton);

        // コンティニューボタン
        continueButton = new JButton("Continue");
        continueButton.setBounds(Player.WINDOW_WIDTH / 2 - 75, Player.WINDOW_HEIGHT / 2 - 100, 150, 50);
        continueButton.addActionListener(e -> restartGame());
        continueButton.setVisible(false); // 初期状態では非表示
        add(continueButton);

        // 終了ボタン
        quitButton = new JButton("Quit");
        quitButton.setBounds(Player.WINDOW_WIDTH / 2 - 75, Player.WINDOW_HEIGHT / 2, 150, 50);
        quitButton.addActionListener(e -> System.exit(0));
        quitButton.setVisible(false); // 初期状態では非表示
        add(quitButton);
    }

    // カウントダウンを開始
    private void startCountdown() {
        gameState = GameState.COUNTDOWN; // カウントダウン状態に変更
        startButton.setVisible(false);
        continueButton.setVisible(false);
        quitButton.setVisible(false);
        countdown = 3; // カウントダウンをリセット

        Timer countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--;
                repaint(); // カウントダウンを更新
                if (countdown <= 0) {
                    ((Timer) e.getSource()).stop(); // タイマーを停止
                    startGame(); // ゲーム開始
                }
            }
        });
        countdownTimer.start();
    }

    // ゲーム開始
    private void startGame() {
        gameState = GameState.PLAYING;
        Timer gameTimer = new Timer(16, e -> {
            if (gameState == GameState.PLAYING) {
                // ゲームロジックの更新
                enemyManager.update(player);
                player.update(enemyManager.getEnemies());

                // 衝突判定
                for (Enemy enemy : enemyManager.getEnemies()) {
                    for (EnemyBullet bullet : enemy.getBullets()) {
                        if (bullet.getBounds().intersects(player.getBounds())) {
                            gameOver();
                            ((Timer) e.getSource()).stop();
                            return;
                        }
                    }

                    if (enemy.getBounds().intersects(player.getBounds())) {
                        gameOver();
                        ((Timer) e.getSource()).stop();
                        return;
                    }
                }

                repaint();
            }
        });
        gameTimer.start();
    }

    // ゲームオーバー
    private void gameOver() {
        gameState = GameState.GAME_OVER;
        continueButton.setVisible(true); // コンティニューボタンを表示
        quitButton.setVisible(true); // 終了ボタンを表示
        repaint();
    }

    // ゲーム再スタート
    private void restartGame() {
        gameState = GameState.COUNTDOWN; // カウントダウンから再スタート
        player.reset(); // プレイヤーの状態をリセット
        enemyManager.reset(); // 敵の状態をリセット
        startCountdown();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (gameState) {
            case TITLE -> drawTitleScreen(g);
            case COUNTDOWN -> drawCountdown(g);
            case PLAYING -> {
                player.draw(g);
                enemyManager.draw(g);
            }
            case GAME_OVER -> drawGameOverScreen(g);
        }
    }

    // タイトル画面を描画
    private void drawTitleScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Player.WINDOW_WIDTH, Player.WINDOW_HEIGHT);
        g.setColor(Color.darkGray);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("シューティングゲーム", Player.WINDOW_WIDTH / 2 - 170, Player.WINDOW_HEIGHT / 2 - 100);
    }

    // カウントダウンを描画
    private void drawCountdown(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Player.WINDOW_WIDTH, Player.WINDOW_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString(String.valueOf(countdown), Player.WINDOW_WIDTH / 2 - 15, Player.WINDOW_HEIGHT / 2 + 10);
    }

    // ゲームオーバー画面を描画
    private void drawGameOverScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Player.WINDOW_WIDTH, Player.WINDOW_HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("ゲームオーバー", Player.WINDOW_WIDTH / 2 - 120, Player.WINDOW_HEIGHT / 2 - 150);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameState == GameState.PLAYING) {
            player.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameState == GameState.PLAYING) {
            player.keyReleased(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 使用しない
    }
}