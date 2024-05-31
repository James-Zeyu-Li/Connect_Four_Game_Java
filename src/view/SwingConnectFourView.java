package view;

import controller.ConnectFourController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import model.Player;

/**
 * View component in the MVC architecture of a Connect Four game. This class defines the core
 * functionalities required to display the game state and messages to the user.
 */
public class SwingConnectFourView extends JFrame implements ConnectFourView {
  private final int rows = 6;
  private final int columns = 7;
  private final JLabel[][] gridLabels = new JLabel[rows][columns];
  private final JPanel boardPanel = new JPanel();
  private final JLabel statusLabel = new JLabel("Welcome to Connect 4", SwingConstants.CENTER);
  private ConnectFourController controller;

  /**
   * Constructs a new SwingConnectFourView object with the specified output destination. The view
   * displays the game board and status messages to the user.
   *
   * @param title the title of the window
   */
  public SwingConnectFourView(String title) {
    super(title);
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

    setSize(850, 700);
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

  }

  /**
   * Displays the message indicating the current player's turn.
   *
   * @param message the message to display
   */
  @Override
  public void displayPlayerTurn(String message) {

  }

  /**
   * Displays the current game state.
   *
   * @param boardState the current game state
   */
  @Override
  public void displayGameState(Player[][] boardState) {

  }

  /**
   * Displays an error message to the user.
   *
   * @param message the error message to display
   */
  @Override
  public void displayError(String message) {

  }

  /**
   * Displays the game over message.
   *
   * @param winner the winner of the game
   */
  @Override
  public void displayGameOver(Player winner) {

  }
}
