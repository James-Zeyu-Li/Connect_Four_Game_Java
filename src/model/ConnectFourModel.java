package model;

import model.enums.Player;

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

  Player getTurn();

  boolean isGameOver();

  Player getWinner();

  void resetBoard();

  Player[][] getBoardState();

}
