
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ScreenPanel extends JPanel implements ActionListener {
    private final int SCREEN_WIDTH = 300;
    private final int SCREEN_HEIGHT = 300;
    private final int DOT_ICON_SIZE = 10;
    private final int DOT_ICONS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
    private int parts;
    private int apple_x;
    private int apple_y;
    private Timer timer;
    private Image snakepart;
    private Image apple;
    private Image snakehead;

    public ScreenPanel() {

        initScreenPanel();
    }

    private void initScreenPanel() {

        addKeyListener(new KeyInput());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        loadIcons();
        initSnakeGame();
    }

    private void initSnakeGame() {

        parts = 5;

        for (int z = 0; z < parts; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void loadIcons() {

        ImageIcon iisnakepart = new ImageIcon("resources/snakepart.png");
        snakepart = iisnakepart.getImage();

        ImageIcon iiapple = new ImageIcon("resources/apple.png");
        apple = iiapple.getImage();

        ImageIcon iisnakehead = new ImageIcon("resources/snakehead.png");
        snakehead = iisnakehead.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
         
        if (inSnakeGame) {
 
            g.drawImage(apple, apple_x, apple_y, this);
 
            for (int z = 0; z  0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
 
        if (leftDirection) {
            x[0] -= DOT_ICON_SIZE;
        }
 
        if (rightDirection) {
            x[0] += DOT_ICON_SIZE;
        }
 
        if (upDirection) {
            y[0] -= DOT_ICON_SIZE;
        }
 
        if (downDirection) {
            y[0] += DOT_ICON_SIZE;
        }
    }

    private void findCollision() {

        for (int z = parts; z > 0; z--) {

            if ((z > 6) && (x[0] == x[z]) && (y[0] == y[z])) {
                inSnakeGame = false;
            }
        }

        if (y[0] >= SCREEN_HEIGHT) {
            inSnakeGame = false;
        }

        if (y[0] = SCREEN_WIDTH) {
            inSnakeGame = false;
        }

        if (x[0] < 0) {
            inSnakeGame = false;
        }

        if (!inSnakeGame) {
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_ICON_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_ICON_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (inSnakeGame) {

            findAppleIcon();
            findCollision();
            shift();
        }

        repaint();
    }

}