package controller;
import model.ConnectFourModel;

public interface ConnectFourController {

  /**
   * Execute a single game of Connect Four given a Connect Four Model. When the game
   * is over, the playGame method ends.
   * The game is played by making moves in the Connect Four game.
   *
   * @throws IllegalArgumentException if the model is null
   */
  void playGame(ConnectFourModel m) throws IllegalArgumentException;


  /**
   * Make a move in the Connect Four game. The move is executed using the model.
   * The move is made in the column specified by the user.
   *
   * @param column the column in which to place the disc
   */
  void makeMove(int column);


  /**
   * Reset the game to its initial state.
   * This method is called when the user wants to play another game.
   * The board is cleared and the current player is set to Player.ONE.
   */
  void resetGame();


  /**
   * Get the status message for the current player's turn.
   * This message is displayed to the user to indicate whose turn it is.
   * The message is either "Player 1's turn" or "Player 2's turn".
   *
   * @return the status message for the current player's turn
   */
  String getStatusMessage();
}