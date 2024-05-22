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
   * Loop through each cell and make it null.
   */
  @Override
  public void initializeBoard() {
    this.board = new Player[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        board[i][j] = null;
      }
    }
  }

  /**
   * Select a column to drop the disc at, the disc will be dropped to the lowest non-occupied row.
   * If column is full, reject the selection
   *
   * @param columnNum the column to drop the disc
   * @throws IllegalArgumentException if column is out of range
   * @throws IllegalStateException    if the game is already over
   */
  @Override
  public void makeMove(int columnNum) throws IllegalArgumentException {
    if (columnNum < 0 || columnNum >= columns) {
      throw new IllegalArgumentException("The column is out of boundary");
    }

    if (gameOver) {
      throw new IllegalStateException("The game is already over");
    }

    if (board[0][columnNum] != null) {
      throw new IllegalArgumentException("The column is full");
    }

    for (int i = rows - 1; i >= 0; i--) {
      if (board[i][columnNum] == null) {
        board[i][columnNum] = getTurn();
        lastRow = i;
        lastColumn = columnNum;
        movesCount++;
        gameOver = isGameOver();
        return;
      }
    }
  }

  /**
   * Retrieves the player whose turn it is to make a move.
   *
   * @return the player whose turn it is
   */
  @Override
  public Player getTurn() {
    if (gameOver) {
      return null;
    }
    return movesCount % 2 == 0 ? Player.RED : Player.YELLOW;
  }


  /**
   * When there is a player's consecutive vertical count of one player, that player wins.
   *
   * @param row    The row number
   * @param column the column number
   * @return Return true if the game is over, false otherwise
   */
  private boolean checkVerticalWin(int row, int column) {
    Player currentPlayer = board[row][column];
    if (currentPlayer == null) {
      return false;
    }

    int count = 1;
    for (int i = row + 1; i < rows && board[i][column] == currentPlayer; i++) {
      count++;
      if (count >= 4) {
        winner = currentPlayer;
        return true;
      }
    }
    return false;
  }


  /**
   * When there is a player with more than or equal to 4 consecutive vertical count, a player win.
   *
   * @param row    The row number
   * @param column The column number
   * @return Return True if the game is over, false otherwise.
   */
  private boolean checkHorizontalWin(int row, int column) {
    Player currentPlayer = board[row][column];
    if (currentPlayer == null) {
      return false;
    }

    int count = 1;
    for (int j = column + 1; j < columns && board[row][j] == currentPlayer; j++) {
      count++;
    }

    for (int j = column - 1; j >= 0 && board[row][j] == currentPlayer; j--) {
      count++;
    }
    
    if (count >= 4) {
      winner = currentPlayer;
      return true;
    }

    return false;

  }

  /**
   * Check
   *
   * @param row
   * @param colum
   * @return
   */
  private boolean checkDiagonalWin(int row, int colum) {

  }

  /**
   * Checks if the game is over. The game is over when either the board is full
   *
   * @return true if the game is over, false otherwise
   */
  private boolean isBoardFull() {
    for (int j = 0; j < columns; j++) {
      if (board[0][j] == null) {
        return false;
      }
    }
    winner = null;
    return true;
  }


  /**
   * Checks if the game is over. The game is over when either the board is full, or
   * one player has won vertically, diagonally or horizontally.
   *
   * @return true if game is over, false otherwise.
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
