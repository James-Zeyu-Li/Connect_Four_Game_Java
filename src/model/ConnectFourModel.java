package model;

/**
 * Interface for the Connect Four Game model component in the MVC architecture.
 * This interface defines the core functionalities for the Connect Four game model.
 * The model is responsible for managing the game state, including the game board,
 * player moves, and win/draw conditions.
 */
public interface ConnectFourModel {

  /**
   * Initializes the game board with a specified number of rows and columns.
   */
  void initializeBoard();

  /**
   * Select a column to drop the disc at, the disc will be dropped to the lowest non-occupied row.
   * If column is full, reject the selection
   *
   * @param columnNum the column to drop the disc
   * @throws IllegalArgumentException if column is out of range
   */
  void makeMove(int columnNum) throws IllegalArgumentException;


  /**
   * Retrieves the player whose turn it is to make a move.
   *
   * @return the player whose turn it is
   */
  Player getTurn();


  /**
   * Checks if the game is over. The game is over when either the board is full, or
   * one player has won vertically, diagonally or horizontally.
   *
   * @return true if game is over, false otherwise.
   */
  boolean isGameOver();


  /**
   * Retrieves the winner of the game, or null if there is no winner. If the game is not
   * over, returns null
   *
   * @return the winner, or null if there is no winner
   */
  Player getWinner();


  /**
   * Reset all the cells in the board for a new game.
   */
  void resetBoard();

  /**
   * Retrieves the current state of the game board. This method is useful
   * for the View component to display the current game status.
   *
   * @return a 2D array representing the current state of the board
   */
  Player[][] getBoardState();

}
