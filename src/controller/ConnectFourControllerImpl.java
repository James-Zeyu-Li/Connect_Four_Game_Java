package controller;

import model.ConnectFourModel;
import view.ConnectFourView;

public class ConnectFourControllerImpl implements ConnectFourController {
  private final ConnectFourModel model;
  private final ConnectFourView view;

  /**
   * Constructs a new SwingConnectFourController object with the specified input source, view, and
   * model. The controller is responsible for handling user moves by executing them using the model
   * and conveying move outcomes to the user.
   *
   * @param view  the view for the Connect Four game
   * @param model the model for the Connect Four game
   */
  public ConnectFourControllerImpl(ConnectFourView view, ConnectFourModel model)
      throws IllegalArgumentException {
    if (view == null || model == null) {
      throw new IllegalArgumentException("View and model cannot be null");
    }

    this.model = model;
    this.view = view;
    this.view.setController(this);
  }


  /**
   * Execute a single game of Connect Four given a Connect Four Model. When the game
   * is over, the playGame method ends.
   * The game is played by making moves in the Connect Four game.
   *
   * @param m
   * @throws IllegalArgumentException if the model is null
   */
  @Override
  public void playGame(ConnectFourModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Model can't be null");
    }

    model.initializeBoard();
    view.displayGameState(model.getBoardState());
    view.displayPlayerTurn(getStatusMessage());
  }

  /**
   * Make a move in the Connect Four game. The move is executed using the model.
   * If the game is over, an IllegalStateException is thrown. If the move is invalid,
   * an IllegalArgumentException is thrown.
   *
   * @param column the column in which to place the disc
   */
  @Override
  public void makeMove(int column) throws IllegalArgumentException {
    if (model.isGameOver()) {
      throw new IllegalStateException("The game is over");
    }

    try {
      model.makeMove(column);

      view.displayGameState(model.getBoardState());
      if (model.isGameOver()) {
        view.displayGameOver(model.getWinner());
      } else {
        view.displayPlayerTurn(getStatusMessage());
      }
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage());
    }
  }

  /**
   * Reset the game to its initial state.
   * This method is called when the user wants to play another game.
   * The board is cleared and the current player is set to Player.ONE.
   */
  @Override
  public void resetGame() {

  }

  /**
   * Get the status message for the current player's turn.
   * This message is displayed to the user to indicate whose turn it is.
   * The message is either "Player 1's turn" or "Player 2's turn".
   *
   * @return the status message for the current player's turn
   */
  @Override
  public String getStatusMessage() {
    return null;
  }
}
