package view;

import controller.ConnectFourController;
import model.Player;



public interface ConnectFourView {
  /**
   * Sets the controller for this view.
   * This method is called by the controller to set itself as the controller for this view.
   *
   * @param controller the controller to set
   */
  void setController(ConnectFourController controller);

  /**
   * This method is called by the controller to initialize the game board display.
   *
   * @param board the board to display
   */
  void updateBoard(Player[][] board);


  /**
   * Displays the message indicating the current player's turn.
   *
   * @param message the message to display
   */
  void displayPlayerTurn(String message);


  /**
   * Displays the current game state.
   *
   * @param boardState the current game state
   */
  void displayGameState(Player[][] boardState);


  /**
   * Displays an error message to the user.
   *
   * @param message the error message to display
   */
  void displayError(String message);


  /**
   * Displays the game over message.
   *
   * @param winner the winner of the game
   */
  void displayGameOver(Player winner);

}

