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
    int counter = 0;

    GridLayout grid = new GridLayout(4, 4, 2, 2);

    GameOf15() {
        setupGameframe();
        updateView();
    }

    private void setupGameframe() {
        buttonPanel.setLayout(grid);
        // Loopar genom HashMapen med knappar och säkerställer att de hamnar i rätt ordning
        // med hjälp av keyn
        for (int buttonNr = 0; buttonNr < buttons.size(); buttonNr++) {
            buttonPanel.add(buttons.get(buttonNr));
        }

        setLayout(new FlowLayout());
        add(buttonPanel);

        newGamePanel.setLayout(new FlowLayout());
        buttonPanel.add(newGamePanel);
        buttonPanel.setPreferredSize(new Dimension(400, 350));

        newGamePanel.add(newGameButton);
        newGameButton.addMouseListener(getMouseListener());
        newGameButton.setName("newgame");
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
                // Sätter texten på knapparna med värden från elementen på gameboard
                buttons.get(i).setText(String.valueOf(number));
                buttons.get(i).setBackground(new Color(179, 250, 125));
            }
        }
    }

    // Skapar knappar och lägger de i HashMap
    private Map<Integer, JButton> createButtons() {
        HashMap<Integer, JButton> buttons = new HashMap<>();
        for (int buttonNr = 0; buttonNr < 16; buttonNr++) {
            JButton button = new JButton();
            // Sätter namnen på knapparna
            button.setName(String.valueOf(buttonNr));
            button.addMouseListener(getMouseListener());
            // Använder för enkelhetens skull även buttonNr som key
            buttons.put((buttonNr), button);
        }
        return buttons;
    }

    private MouseListener getMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String buttonName = e.getComponent().getName();
                if (buttonName.equals("newgame")) {
                    gameBoard = new GameBoard();
                    counter = 0;
                } else {
                    if (gameBoard.moveTile(Integer.parseInt(buttonName))) {
                        counter++;
                    }
                    if (gameBoard.checkWin()) {
                        displayWinDialogue();
                    }
                }
                updateView();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                String name = e.getComponent().getName();
                if (!name.equals("newgame") && gameBoard.isValidMove(Integer.parseInt(name))) {
                    // Sätter färgen på knapparna som går att flytta till grön
                    e.getComponent().setBackground(Color.GREEN);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                String name = e.getComponent().getName();
                if (!name.equals("newgame") && e.getComponent().getBackground().equals(Color.GREEN)) {
                    e.getComponent().setBackground(new Color(179, 250, 125));
                }
            }
        };
    }

    private void displayWinDialogue() {
        JOptionPane.showMessageDialog(null, "Grattis! Du vann! Det tog " + counter + " antal drag!");
    }

    public static void main(String[] args) {
        GameOf15 gameOf15 = new GameOf15();
    }
}