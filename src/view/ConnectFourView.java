package view;

import controller.ConnectFourController;
import model.Player;

/**
 * Interface for the View component in the MVC architecture of a Connect Four game.
 * This interface defines the core functionalities required to display the game state
 * and messages to the user.
 */
public interface ConnectFourView {
  /**
   * Sets the controller for this view.
   * This method is called by the controller to set itself as the controller for this view.
   *
   * @param controller the controller to set
   */
  void setController(ConnectFourController controller);

  /**
   * Updates the game board display based on the current state of the board. The background color of
   * each cell is set to the color of the player occupying the cell.
   * The background color of empty cells is set to white.
   * The background color of cells occupied by red players is set to red.
   * The background color of cells occupied by yellow players is set to yellow.
   *
   * @param board the board to display
   */
  void updateBoard(Player[][] board);


  /**
   * Displays the current player's turn.
   * The message is displayed in the center of the status label.
   *
   * @param message the message to display
   */
  void displayPlayerTurn(String message);


  /**
   * Displays the current game state. The game state is displayed by updating the game board.
   *
   * @param boardState the current game state
   */
  void displayGameState(Player[][] boardState);


  /**
   * Displays an error message to the user. The error message is displayed in a dialog box.
   *
   * @param message the error message to display
   */
  void displayError(String message);


  /**
   * Displays the game over message. The message is displayed in a dialog box.
   * The message includes the winner of the game or a draw game message.
   * The user is prompted to play again or exit the game.
   * If the user chooses to play again, the controller's resetGame method is called.
   * If the user chooses to exit the game, the window is closed.
   *
   * @param winner the winner of the game
   */
  void displayGameOver(Player winner);

}

