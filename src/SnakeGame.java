import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JPanel {
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
    int tileSize = 25;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);
    }
}
