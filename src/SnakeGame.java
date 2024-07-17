import javax.swing.*;
import java.awt.*;

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

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);

        // Posição inicial da cabeça da cobra
        snakeHead = new Tile(5, 5);
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

        //Snake
        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }
}
