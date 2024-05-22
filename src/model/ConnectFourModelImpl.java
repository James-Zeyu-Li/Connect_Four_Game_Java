package model;

import model.enums.Player;

/**
 * Class for the Connect Four game model.
 */
public class ConnectFourModelImpl implements ConnectFourModel {
  protected final int rows;
  protected final int columns;
  protected Player[][] board;
  protected int movesCount;
  protected boolean gameOver;
  protected Player winner;
  protected int lastRow = -1;
  protected int lastColumn = -1;

  /**
   * Constructor for the ConnectFourModelImpl class.
   *
   * @param rows    the number of rows in the game board
   * @param columns the number of columns in the game board
   */
  public ConnectFourModelImpl(int rows, int columns) throws IllegalArgumentException {
    if (rows < 4 || columns < 4) {
      throw new IllegalArgumentException("The number of rows and columns must be at least 4.");
    }
    this.rows = rows;
    this.columns = columns;
    this.board = new Player[rows][columns];
    this.movesCount = 0;
    this.gameOver = false;
    this.winner = null;
    resetBoard();
  }


  /**
   * Initializes the game board with a specified number of rows and columns.
   */
  @Override
  public void initializeBoard() {

  }

  /**
   * @param column
   * @throws IllegalArgumentException
   */
  @Override
  public void makeMove(int column) throws IllegalArgumentException {

  }

  /**
   * @return
   */
  @Override
  public Player getTurn() {
    return null;
  }

  /**
   * @return
   */
  @Override
  public boolean isGameOver() {
    return false;
  }

  /**
   * @return
   */
  @Override
  public Player getWinner() {
    return null;
  }

  /**
   *
   */
  @Override
  public void resetBoard() {

  }

  /**
   * @return
   */
  @Override
  public Player[][] getBoardState() {
    return new Player[0][];
  }
}
