package view;

import controller.ConnectFourController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import model.Player;

/**
 * View component in the MVC architecture of a Connect Four game. This class defines the core
 * functionalities required to display the game state and messages to the user.
 */
public class SwingConnectFourView extends JFrame implements ConnectFourView {
  private final int rows;
  private final int columns;
  private final JLabel[][] gridLabels;
  private final JPanel boardPanel = new JPanel();
  private final JLabel statusLabel = new JLabel("Welcome to Connect 4", SwingConstants.CENTER);
  private ConnectFourController controller;

  /**
   * Constructs a new SwingConnectFourView object with the specified output destination. The view
   * displays the game board and status messages to the user.
   *
   * @param title   the title of the window
   * @param rows    the number of rows in the game board
   * @param columns the number of columns in the game
   */
  public SwingConnectFourView(String title, int rows, int columns) {
    super(title);
    this.rows = rows;
    this.columns = columns;
    this.gridLabels = new JLabel[rows][columns];
    initializeView();
  }

  /**
   * Initializes the view. To set up the game board, status label, and control buttons.
   * The game board is a 6x7 grid of JLabels, each representing a cell in the Connect Four board.
   * The status label displays the current player's turn and game status.
   */
  private void initializeView() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Initialize the board panel
    add(statusLabel, BorderLayout.NORTH);
    boardPanel.setLayout(new GridLayout(rows, columns));
    initializeBoard();
    add(boardPanel, BorderLayout.CENTER);

    // Initialize the reset and exit buttons
    JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton resetButton = new JButton("Reset Game");
    resetButton.addActionListener(e -> {
      controller.resetGame();
      statusLabel.setText("Game restarted. Red's turn.");
    });
    JButton exitButton = new JButton("Exit Game");
    exitButton.addActionListener(e -> System.exit(0));
    controlPanel.add(resetButton);
    controlPanel.add(exitButton);
    add(controlPanel, BorderLayout.SOUTH);

    setSize(850, 700);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Initializes the game board display.
   * The game board is a 6x7 grid of JLabels, each representing a cell in the Connect Four board.
   * The JLabels are added to the board panel.
   * The background color of each cell is set to white.
   * The border of each cell is set to black.
   * The mouse listener is added to each cell to handle user input.
   * When a cell is clicked, the controller's makeMove method is called with the column index.
   */
  private void initializeBoard() {
    boardPanel.removeAll();
    boardPanel.setLayout(new GridLayout(rows, columns));

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        JLabel cellLabel = new JLabel("", SwingConstants.CENTER);
        cellLabel.setOpaque(true);
        cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cellLabel.setBackground(Color.WHITE);

        final int column = col;
        cellLabel.addMouseListener(new java.awt.event.MouseAdapter() {
          @Override
          public void mousePressed(java.awt.event.MouseEvent evt) {
            controller.makeMove(column);
          }
        });
        boardPanel.add(cellLabel);
        gridLabels[row][col] = cellLabel;
      }
    }
    boardPanel.revalidate();
    boardPanel.repaint();
  }


  /**
   * Sets the controller for this view.
   * This method is called by the controller to set itself as the controller for this view.
   *
   * @param controller the controller to set
   */
  @Override
  public void setController(ConnectFourController controller) {
    this.controller = controller;
  }

  /**
   * Updates the game board display based on the current state of the board. The background color of
   * each cell is set to the color of the player occupying the cell.
   * The background color of empty cells is set to white.
   * The background color of cells occupied by red players is set to red.
   * The background color of cells occupied by yellow players is set to yellow.
   *
   * @param board the board to display
   */
  @Override
  public void updateBoard(Player[][] board) {
    SwingUtilities.invokeLater(() -> {
      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < columns; col++) {
          JLabel label = gridLabels[row][col];
          if (board[row][col] == Player.RED) {
            label.setBackground(Color.RED);
          } else if (board[row][col] == Player.YELLOW) {
            label.setBackground(Color.YELLOW);
          } else {
            label.setBackground(Color.WHITE);
          }
        }
      }
    });
  }

  /**
   * Displays the current player's turn.
   * The message is displayed in the center of the status label.
   *
   * @param message the message to display
   */
  @Override
  public void displayPlayerTurn(String message) {
    statusLabel.setText(message);
  }

  /**
   * Displays the current game state. The game state is displayed by updating the game board.
   *
   * @param boardState the current game state
   */
  @Override
  public void displayGameState(Player[][] boardState) {
    updateBoard(boardState);
  }

  /**
   * Displays an error message to the user. The error message is displayed in a dialog box.
   *
   * @param message the error message to display
   */
  @Override
  public void displayError(String message) {
    JOptionPane.showMessageDialog(
        this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Displays the game over message. The message is displayed in a dialog box.
   * The message includes the winner of the game or a draw game message.
   * The user is prompted to play again or exit the game.
   * If the user chooses to play again, the controller's resetGame method is called.
   * If the user chooses to exit the game, the window is closed.
   *
   * @param winner the winner of the game
   */
  @Override
  public void displayGameOver(Player winner) {
    String message = winner != null ? winner + " wins!" : "Draw Game";
    int result = JOptionPane.showConfirmDialog(
        this, message + "\nPlay again?", "Game Over",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (result == JOptionPane.YES_OPTION) {
      controller.resetGame();
      statusLabel.setText("Game restarted. Red's turn.");
    } else {
      dispose();
    }
  }
}