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

  void makeMove(int column) throws IllegalArgumentException;

  Player getTurn();

  boolean isGameOver();

  Player getWinner();

  void resetBoard();

  Player[][] getBoardState();

}
