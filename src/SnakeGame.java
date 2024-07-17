import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SnakeGame extends JPanel {
    // posição dos blocos
    private class Tile{
        int x;
        int y;

        Tile(int x, int y){
            this.x = x;
            this.y = x;
        }
    }


    int boardWidth;
    int boardHeight;

    // Tamanho do blocos
    int tileSize = 25;

    Tile snakeHead;

    Tile food;

    Random random;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);

        // Posição inicial da cabeça da cobra
        snakeHead = new Tile(5, 5);

        // Posição inicial da fruta
        food = new Tile(10, 10);
        random = new Random();
        placeFood();
    }

    // Função para pintar na tabuleiro

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    // função para desenhar no tabuleiro
    public void draw(Graphics g) {

        // Grid
        for (int i = 0; i < boardWidth/tileSize; i++){ //  calcula quantas colunas de blocos de tamanho tileSize
            // x1, y1, x2, y2
            g.drawLine(i*tileSize, 0 , i*tileSize, boardHeight); // Cria a linha vertical
            g.drawLine(0, i*tileSize, boardWidth, i*tileSize); // Crua a linha Horizontal
        }

        // Fruit
        g.setColor(Color.RED);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);


        //Snake
        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    // Responsável por criar a posição aleatória da fruta
    public void placeFood() {
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }
}
