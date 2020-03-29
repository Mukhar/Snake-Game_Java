import javax.swing.*;
import java.awt.*;
public class Game {

    public static final int DIRECTION_NONE = 0, DIRECTION_RIGHT = 1, DIRECTION_LEFT = -1, DIRECTION_UP = 2,
            DIRECTION_DOWN = -2;
    private Snake snake;
    private Board board;
    private int direction;
    private boolean gameOver;

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
        // snake initial position is not origin
        Cell initPos = new Cell(1, 1);
        Snake initSnake = new Snake(initPos);
        Board board = new Board(10, 10);
        Game newGame = new Game(initSnake, board);

        //Creating the Frame
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu file_option = new JMenu("FILE");
        mb.add(file_option);
        JMenuItem m11 = new JMenuItem("New Game");
        JMenuItem m22 = new JMenuItem("Save as");
        file_option.add(m11);
        file_option.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Welcome Player");
        JButton restart_button = new JButton("Restart");
        panel.add(label); // Components Added using Flow Layout
        panel.add(restart_button);

        // GUI area
        JPanel gui = new JPanel(new GridLayout(10,10,2,2));
        gui.setBackground(Color.BLACK);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, gui);
        frame.pack();
        frame.setVisible(true);


        newGame.gameOver = false;
        newGame.direction = DIRECTION_RIGHT;

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
}