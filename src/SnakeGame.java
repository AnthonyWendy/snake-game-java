import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    // posição dos blocos
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;

    // Tamanho do blocos
    int tileSize = 25;

    // Snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    // Fruit
    Tile food;
    Random random;

    // Game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        // Posição inicial da cabeça da cobra
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        // Posição inicial da fruta
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 1;

        gameLoop = new Timer(120, this);
        gameLoop.start();
    }

    // Função para pintar no tabuleiro
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // função para desenhar no tabuleiro
    public void draw(Graphics g) {
        // Fruit
        g.setColor(Color.RED);
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        // Snake head
        g.setColor(Color.GREEN);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize, true);

        // Snake body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        // Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over: " + snakeBody.size(), tileSize, tileSize);
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Score: " + snakeBody.size(), tileSize, tileSize);
        }
    }

    // Responsável por criar a posição aleatória da fruta
    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize);
        food.y = random.nextInt(boardHeight / tileSize);
    }

    // Para saber se a Snake bateu na parede ou em si mesma
    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {
        // Come a fruta
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        // Move o corpo da cobra
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                snakePart.x = snakeBody.get(i - 1).x;
                snakePart.y = snakeBody.get(i - 1).y;
            }
        }

        // Move a cabeça da cobra
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Verifica colisão com o corpo
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }

        // Verifica colisão com as bordas do tabuleiro
        if (snakeHead.x < 0 || snakeHead.x >= boardWidth / tileSize ||
                snakeHead.y < 0 || snakeHead.y >= boardHeight / tileSize) {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}