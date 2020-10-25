import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class GameOf15 extends JFrame {

    GameBoard gameBoard = new GameBoard();
    Map<Integer, JButton> buttons = createButtons();
    JButton newGameButton = new JButton("New Game");
    JPanel buttonPanel = new JPanel();
    JPanel newGamePanel = new JPanel();

    GridLayout grid = new GridLayout(4, 4, 1, 1);

    GameOf15() {
        boolean play = true;
        setupGameframe();
        while (play) {
            updateView();
        }
    }

    private void setupGameframe() {
        buttonPanel.setLayout(grid);
        for (Map.Entry<Integer, JButton> entry : buttons.entrySet()) {
            buttonPanel.add(entry.getValue(), entry.getKey());
        }

        setLayout(new FlowLayout());
        add(buttonPanel);

        newGamePanel.setLayout(new FlowLayout());
        buttonPanel.add(newGamePanel);
        buttonPanel.setPreferredSize(new Dimension(400, 350));

        newGamePanel.add(newGameButton);
        newGameButton.addMouseListener(getMouseListener());
        newGamePanel.setBackground(Color.LIGHT_GRAY);

        add(newGamePanel);
        pack();
        setSize(450, 450);
        setResizable(false);
        setTitle("15 Puzzle-Game");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        System.out.println(Arrays.toString(gameBoard.getGameBoard()));
    }

    // Mappar innehållet från gameboard till rätt knapp
    private void updateView() {
        int[] gameBoard = this.gameBoard.getGameBoard();
        for (int i = 0; i < gameBoard.length; i++) {
            int number = gameBoard[i];
            if (number == 0) {
                buttons.get(i).setBackground(Color.white);
                buttons.get(i).setText("");
            } else {
                buttons.get(i).setText(String.valueOf(number));
                buttons.get(i).setBackground(Color.lightGray);
            }
        }
    }

    // Skapar knappar och lägger de i HashMap
    private Map<Integer, JButton> createButtons() {
        HashMap<Integer, JButton> buttons = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            JButton button = new JButton();
            button.setName(String.valueOf(i));
            button.addMouseListener(getMouseListener());
            buttons.put((i), button);
        }
        return buttons;
    }

    private MouseListener getMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameBoard.moveTile(Integer.parseInt(e.getComponent().getName()));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        };
    }

    public static void main(String[] args) {
        GameOf15 gameOf15 = new GameOf15();
    }
}