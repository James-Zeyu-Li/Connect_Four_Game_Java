import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import model.ConnectFourModel;
import model.ConnectFourModelImpl;
import model.enums.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Test ConnectFourModel class.
 */
public class ConnectFourModelTest {
  private ConnectFourModel model;

  @Before
  public void setup() {
    model = new ConnectFourModelImpl(6, 7);
  }

  @Test
  public void testWinnerIsNullAtBeginning() {
    assertNull("Winner should be null at the beginning of the game", model.getWinner());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBoardInitialization() {
    new ConnectFourModelImpl(3, 3);
  }

  @Test
  public void testBoardInitialization() {
    Player[][] boardState = model.getBoardState();
    for (Player[] row : boardState) {
      for (Player cell : row) {
        assertNull("Board should be initialized empty", cell);
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeNegativeMove() {
    model.makeMove(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeMoveOutOfBounds() {
    model.makeMove(7);
  }


  @Test(expected = IllegalStateException.class)
  public void testMakeMoveAfterGameOver() {
    for (int i = 0; i < 4; i++) {
      model.makeMove(0);
      if (i < 3) {
        model.makeMove(1);
      }
    }
    assertTrue("Game should be over after four consecutive vertical moves",
        model.isGameOver());

    model.makeMove(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeMoveThrowsExceptionWhenColumnIsFull() {
    int column = 0;
    for (int i = 0; i < 7; i++) {
      model.makeMove(column);
    }
    model.makeMove(column);
  }

  @Test
  public void testMakeMove() {
    model.makeMove(0);
    assertEquals("Player 1 should have made a move", Player.RED, model.getBoardState()[5][0]);
    model.makeMove(0);
    assertEquals("Player 2 should have made a move", Player.YELLOW, model.getBoardState()[4][0]);
  }

  @Test
  public void testAlternatingTurns() {
    Player initialPlayer = model.getTurn();
    model.makeMove(0);
    assertNotEquals("Turn should alternate after a move",
        initialPlayer, model.getTurn());

    Player secondPlayer = model.getTurn();
    model.makeMove(1);
    assertNotEquals("Turn should alternate after a move",
        secondPlayer, model.getTurn());

    assertEquals("Turn should alternate back to the initial player",
        initialPlayer, model.getTurn());
  }

  @Test
  public void testGetTurn() {
    assertEquals("Player 1 should start", Player.RED, model.getTurn());
    model.makeMove(0);
    assertEquals("Player 2 should play next", Player.YELLOW, model.getTurn());
    model.makeMove(1);
    assertEquals("Player 1 should play next", Player.RED, model.getTurn());
    model.makeMove(2);
    assertEquals("Player 2 should play next", Player.YELLOW, model.getTurn());
    model.makeMove(6);
    assertEquals("Player 1 should play next", Player.RED, model.getTurn());
    model.makeMove(5);
    assertEquals("Player 2 should play next", Player.YELLOW, model.getTurn());
  }

  @Test
  public void testGetTurnReturnsNullWhenGameIsOver() {
    for (int i = 0; i < 4; i++) {
      model.makeMove(0);
      if (i < 3) {
        model.makeMove(1);
      }
    }
    assertTrue("Game should be over", model.isGameOver());
    assertNull("getTurn should return null when the game is over", model.getTurn());
  }

  @Test
  public void testIsGameOverVerticalWinRed() {
    for (int i = 0; i < 4; i++) {
      model.makeMove(0);
      if (i < 3) {
        model.makeMove(1);
      }
    }
    assertTrue("Game should be over after four consecutive vertical moves",
        model.isGameOver());
    assertEquals("Player 1 should win", Player.RED, model.getWinner());
  }

  @Test
  public void testIsGameOverVerticalWinYellow() {

    model.makeMove(6);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);

    assertTrue("Game should be over after four consecutive vertical moves", model.isGameOver());
    assertEquals("Player YELLOW should win", Player.YELLOW, model.getWinner());
  }

  @Test
  public void testIsGameOverHorizontalWinRed() {
    for (int i = 0; i < 4; i++) {
      model.makeMove(i);
      if (i < 3) {
        model.makeMove(i);
      }
    }
    assertTrue("Game should be over after four consecutive horizontal moves",
        model.isGameOver());
    assertEquals("Player 1 should win", Player.RED, model.getWinner());
  }

  @Test
  public void testIsGameOverHorizontalWinYellow() {
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(2);
    model.makeMove(0);
    model.makeMove(3);
    model.makeMove(1);
    model.makeMove(4);

    assertTrue("Game should be over after four consecutive horizontal moves",
        model.isGameOver());
    assertEquals("Player YELLOW should win", Player.YELLOW, model.getWinner());
  }

  @Test
  public void testIsGameOverDiagonalWinTopLeftRed() {
    model.makeMove(3);
    model.makeMove(2);
    model.makeMove(2);
    model.makeMove(1);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(0);
    model.makeMove(4);
    model.makeMove(0);

    assertTrue("Game should be over after four consecutive diagonal moves",
        model.isGameOver());
    assertEquals("Player RED should win with a diagonal line",
        Player.RED, model.getWinner());
  }

  @Test
  public void testIsGameOverDiagonalWinTopLeftYellow() {
    model.makeMove(0);
    model.makeMove(3);
    model.makeMove(2);
    model.makeMove(2);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(0);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(1);

    assertTrue("Game should be over after four consecutive diagonal moves",
        model.isGameOver());
    assertEquals("Player YELLOW should win with a diagonal line",
        Player.YELLOW, model.getWinner());
  }

  @Test
  public void testIsGameOverDiagonalWinTopRightRed() {
    model.makeMove(3);
    model.makeMove(4);
    model.makeMove(4);
    model.makeMove(5);
    model.makeMove(5);
    model.makeMove(6);
    model.makeMove(5);
    model.makeMove(6);
    model.makeMove(6);
    model.makeMove(2);
    model.makeMove(6);

    assertTrue("Game should be over after four consecutive diagonal moves",
        model.isGameOver());
    assertEquals("Player YELLOW should win with a diagonal line",
        Player.RED, model.getWinner());
  }

  @Test
  public void testIsGameOverDiagonalWinTopRightYellow() {
    model.makeMove(6);
    model.makeMove(3);
    model.makeMove(4);
    model.makeMove(4);
    model.makeMove(5);
    model.makeMove(6);
    model.makeMove(6);
    model.makeMove(6);
    model.makeMove(5);
    model.makeMove(5);

    assertTrue("Game should be over after four consecutive diagonal moves",
        model.isGameOver());
    assertEquals("Player YELLOW should win with a diagonal line",
        Player.YELLOW, model.getWinner());
  }

  @Test
  public void testNoWinGameNotOver() {
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(2);
    assertFalse("Should not be game over", model.isGameOver());
  }

  @Test
  public void testBoardFullDraw() {

    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);

    model.makeMove(2);
    model.makeMove(3);
    model.makeMove(2);
    model.makeMove(3);
    model.makeMove(2);
    model.makeMove(3);
    model.makeMove(3);
    model.makeMove(2);
    model.makeMove(3);
    model.makeMove(2);
    model.makeMove(3);
    model.makeMove(2);

    model.makeMove(4);
    model.makeMove(5);
    model.makeMove(4);
    model.makeMove(5);
    model.makeMove(4);
    model.makeMove(5);
    model.makeMove(5);
    model.makeMove(4);
    model.makeMove(5);
    model.makeMove(4);
    model.makeMove(5);
    model.makeMove(4);

    model.makeMove(6);
    model.makeMove(6);
    model.makeMove(6);
    model.makeMove(6);
    model.makeMove(6);
    model.makeMove(6);

    assertTrue("The game should be over when the board is full", model.isGameOver());
    assertNull("There should be no winner if the game ends in a draw", model.getWinner());
  }

  @Test
  public void testGetWinnerReturnsNullForNoWin() {
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    model.makeMove(0);
    model.makeMove(1);
    assertNull(model.getWinner());
  }

  @Test
  public void testGetWinner() {
    for (int i = 0; i < 4; i++) {
      model.makeMove(0);
      if (i < 3) {
        model.makeMove(1);
      }
    }
    assertEquals("Player 1 should win", Player.RED, model.getWinner());
  }

  @Test
  public void testResetBoard() {
    for (int i = 0; i < 4; i++) {
      model.makeMove(0);
      if (i < 3) {
        model.makeMove(1);
      }
    }

    model.resetBoard();
    Player[][] boardState = model.getBoardState();
    for (Player[] row : boardState) {
      for (Player cell : row) {
        assertNull("Board should be initialized empty", cell);
      }
    }
  }

  @Test
  public void testEmptyBoardToString() {
    String expected =
        " | 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n"
            + ("""
            |   |   |   |   |   |   |   |
             ---+---+---+---+---+---+---+
            """).repeat(6);
    assertEquals(expected, model.toString());
  }

  @Test
  public void testBoardWithOneMoveToString() {
    model.makeMove(0); // Player RED makes a move in column 1
    String expected =
        " | 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n"
            + ("""
            |   |   |   |   |   |   |   |
             ---+---+---+---+---+---+---+
            """).repeat(5)
            + "| R |   |   |   |   |   |   |\n"
            + " ---+---+---+---+---+---+---+\n";
    assertEquals(expected, model.toString());
  }

  @Test
  public void testBoardWithSeveralMovesToString() {
    model.makeMove(0); // Player RED
    model.makeMove(1); // Player YELLOW
    model.makeMove(0); // Player RED
    String expected =
        " | 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n"
            + ("""
            |   |   |   |   |   |   |   |
             ---+---+---+---+---+---+---+
            """).repeat(4)
            + "| R |   |   |   |   |   |   |\n"
            + " ---+---+---+---+---+---+---+\n"
            + "| R | Y |   |   |   |   |   |\n"
            + " ---+---+---+---+---+---+---+\n";
    assertEquals(expected, model.toString());
  }

}
