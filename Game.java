import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;

public class Game extends Canvas implements Runnable {

    public static final int w = 320;
    public static final int h = w / (12 * 9);
    public static final int scale = 2;

    public static final int DIRECTION_NONE = 0, DIRECTION_RIGHT = 1, DIRECTION_LEFT = -1, DIRECTION_UP = 2,
            DIRECTION_DOWN = -2;
    private Snake snake;
    private Board board;
    private int direction;
    private boolean gameOver;
    private boolean running = false;

    private Thread thread;

    private synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);

    }

    public Game(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void update() {
        System.out.println("Going to update the game");
        if (!gameOver) {
            if (direction != DIRECTION_NONE) {
                Cell nextCell = getNextCell(snake.getHead());

                if (snake.checkCrash(nextCell)) {
                    setDirection(DIRECTION_NONE);
                    gameOver = true;
                } else {
                    snake.move(nextCell);
                    if (nextCell.celltype == CellType.FOOD) {
                        snake.grow();
                        board.generateFood();
                    }
                }
            }
        }
    }

    public Cell getNextCell(Cell currentPosition) {
        System.out.println("Going to find next cell");
        int row = currentPosition.row;
        int col = currentPosition.col;

        if (direction == DIRECTION_RIGHT) {
            col++;
        } else if (direction == DIRECTION_LEFT) {
            col--;
        } else if (direction == DIRECTION_UP) {
            row--;
        } else if (direction == DIRECTION_DOWN) {
            row++;
        }

        Cell nextCell = board.getCells()[row][col];

        return nextCell;
    }

    public static void main(String[] args) {

        System.out.println("Going to start game");
        // snake initial position is origin
        Cell initPos = new Cell(0, 0);
        Snake initSnake = new Snake(initPos);
        Board board = new Board(100, 100);
        Game newGame = new Game(initSnake, board);

        newGame.setPreferredSize(new Dimension(w * scale, h * scale));
        newGame.setMinimumSize(new Dimension(w * scale, h * scale));

        newGame.gameOver = false;
        newGame.direction = DIRECTION_RIGHT;

        // Creating the Frame
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // GUI area
        JPanel gui = new JPanel(new GridLayout(100, 200, 4, 4));
        gui.setBackground(Color.BLACK);

        frame.getContentPane().add(BorderLayout.CENTER, gui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        newGame.start();

        // We need to update the game at regular intervals,
        // and accept user input from the Keyboard.
        for (int i = 0; i < 5; i++) {
            if (i == 2)
                newGame.board.generateFood();
            newGame.update();
            if (i == 3)
                newGame.direction = DIRECTION_RIGHT;
            if (newGame.gameOver == true)
                break;
        }

    }

    @Override
    public void run() {
        // game loop
        while (running) {

            // System.out.println("working");
        }
        stop();
    }
}