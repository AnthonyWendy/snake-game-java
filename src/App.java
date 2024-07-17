import javax.swing.*;

public class App {
    public static void main(String[] args) {
        int boardWidth = 600;
        int boardHeight = boardWidth;

        JFrame frame = new JFrame("Snake");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // criacao do tabuleiro
        SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);

        // inserindo o tabuleiro na janela
        frame.add(snakeGame);
        frame.pack();
    }
}