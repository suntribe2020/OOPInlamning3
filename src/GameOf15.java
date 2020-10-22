import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Katri Vidén
 * Date: 2020-10-21
 * Time: 15:00
 * Project: OOPInlämning3
 * Copyright: MIT
 */
public class GameOf15 extends JFrame implements ActionListener {

    GameBoard gameBoard = new GameBoard();
    Map<Integer, JButton> buttons = createButtons();
    JFrame frame = new JFrame("Game of 15");
    JPanel mainPanel = new JPanel();

    GridLayout grid = new GridLayout(4, 4, 5, 5);

    GameOf15() {
        /*
        JButton newGameButton = new JButton("New Game");
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        };
        newGameButton.addMouseListener(mouseListener);
        */
        setupGameframe();
        updateView();
    }

    private void setupGameframe() {
        frame.setLayout(grid);
        for (Map.Entry<Integer, JButton> entry : buttons.entrySet()) {
            frame.add(entry.getValue(), entry.getKey());
        }

        frame.pack();
        frame.setSize(350, 350);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println(Arrays.toString(gameBoard.getGameBoard()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Mappar innehållet från gameboard till rätt knapp
    private void updateView() {
        int[] gameBoard = this.gameBoard.getGameBoard();
        for (int i = 0; i < gameBoard.length; i++) {
            int number = gameBoard[i];
            if (number == 0) {
                buttons.get(i).setBackground(Color.white);
            } else {
                buttons.get(i).setText(String.valueOf(number));
            }
        }
    }

    // Skapar knappar och lägger de i hashMap
    private Map<Integer, JButton> createButtons() {
        HashMap<Integer, JButton> buttons = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            buttons.put((i), new JButton());
        }
        return buttons;
    }

    public static void main(String[] args) {
        GameOf15 gameOf15 = new GameOf15();
    }
}