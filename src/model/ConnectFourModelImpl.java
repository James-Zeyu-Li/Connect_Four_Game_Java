package model;

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
      throw new IllegalArgumentException("Column " + columnNum + " is out of boundary. Valid columns are between 0 and " + (columns - 1));
    }

    if (gameOver) {
      throw new IllegalStateException("The game is already over");
    }

    if (board[0][columnNum] != null) {
      throw new IllegalArgumentException("The column " + columnNum + " is full");
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

    if (movesCount % 2 == 0) {
      return Player.RED;
    } else {
      return Player.YELLOW;
    }
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
   * Check if there is a consecutive count of one player's color up to 4,
   * from bottom right to top left.
   *
   * @param row    the row number
   * @param column the column number
   * @return True when the game is over, false otherwise.
   */
  private boolean checkWinTopLeftBottomRight(int row, int column) {
    Player currentPlayer = board[row][column];
    if (currentPlayer == null) {
      return false;
    }

    int count1 = 1;
    for (int i = 1; row - i >= 0 && column - i >= 0; i++) {
      if (board[row - i][column - i] == currentPlayer) {
        count1++;
      } else {
        break;
      }
    }

    for (int i = 1; row + i < rows && column + i < columns; i++) {
      if (board[row + i][column + i] == currentPlayer) {
        count1++;
      } else {
        break;
      }
    }

    if (count1 >= 4) {
      winner = currentPlayer;
      return true;
    }
    return false;
  }

  /**
   * Check if there is a consecutive count of one player's color up to 4,
   * from bottom left to upper right.
   *
   * @param row    the row number
   * @param column the column number
   * @return True when the game is over, false otherwise.
   */
  private boolean checkWinTopRightBottomLeft(int row, int column) {
    Player currentPlayer = board[row][column];
    if (currentPlayer == null) {
      return false;
    }

    int count2 = 1;
    for (int i = 1; row - i >= 0 && column + i < columns; i++) {
      if (board[row - i][column + i] == currentPlayer) {
        count2++;
      } else {
        break;
      }
    }

    for (int i = 1; row + i < rows && column - i >= 0; i++) {
      if (board[row + i][column - i] == currentPlayer) {
        count2++;
      } else {
        break;
      }
    }
    if (count2 >= 4) {
      winner = currentPlayer;
      return true;
    }
    return false;
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
    if (lastRow != -1 && lastColumn != -1) {
      if (checkVerticalWin(lastRow, lastColumn)
          || checkHorizontalWin(lastRow, lastColumn)
          || checkWinTopLeftBottomRight(lastRow, lastColumn)
          || checkWinTopRightBottomLeft(lastRow, lastColumn)) {
        gameOver = true;
        return true;
      }
    }

    if (isBoardFull()) {
      gameOver = true;
      return true;
    }

    return false;
  }


  /**
   * Retrieves the winner of the game, or null if there is no winner. If the game is not
   * over, returns null
   *
   * @return the winner, or null if there is no winner
   */
  @Override
  public Player getWinner() {
    if (isGameOver()) {
      return winner;
    }
    return null;
  }

  /**
   * Reset all the cells in the board for a new game.
   */
  @Override
  public void resetBoard() {
    initializeBoard();
    this.movesCount = 0;
    this.gameOver = false;
    this.winner = null;
    lastRow = -1;
    lastColumn = -1;
  }

  /**
   * Retrieves the current state of the game board. This method is useful
   * for the View component to display the current game status.
   *
   * @return a 2D array representing the current state of the board
   */
  @Override
  public Player[][] getBoardState() {
    Player[][] copy = new Player[rows][columns];
    for (int i = 0; i < rows; i++) {
      System.arraycopy(board[i], 0, copy[i], 0, columns);
    }
    return copy;
  }

  /**
   * Returns a string representation of the game board.
   * Keep for testing
   *
   * @return a string representation of the game board
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Player[][] boardCopy = getBoardState();

    sb.append(" |");
    for (int col = 0; col < columns; col++) {
      sb.append(" ").append(col + 1).append(" |");
    }
    sb.append("\n");

    for (int row = 0; row < rows; row++) {
      sb.append("|");
      for (int col = 0; col < columns; col++) {
        String cellRepresentation = boardCopy[row][col] == null ? "   |" :
            " " + boardCopy[row][col].toString().charAt(0) + " |";
        sb.append(cellRepresentation);
      }
      sb.append("\n");
      sb.append(" ").append("---+".repeat(columns)).append("\n");
    }
    return sb.toString();
  }
}
