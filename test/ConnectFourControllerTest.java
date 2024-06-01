import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import controller.ConnectFourController;
import controller.ConnectFourControllerImpl;
import model.ConnectFourModel;
import model.ConnectFourModelImpl;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import view.ConnectFourView;

/**
 * Test ConnectFourController class. This class tests the controller class by creating a mock view
 * and model and testing the controller methods.
 */
public class ConnectFourControllerTest {
  static class MockConnectFourView implements ConnectFourView {
    boolean gameOverDisplayed = false;
    Player winnerDisplayed;
    String displayedError;
    String playerTurnMessage;
    Player[][] lastBoardState;


    @Override
    public void setController(ConnectFourController controller) {
    }

    @Override
    public void updateBoard(Player[][] board) {
      lastBoardState = board;
    }

    @Override
    public void displayPlayerTurn(String message) {
      playerTurnMessage = message;
    }

    @Override
    public void displayGameState(Player[][] boardState) {
      updateBoard(boardState);
    }

    @Override
    public void displayError(String message) {
      displayedError = message;
    }

    @Override
    public void displayGameOver(Player winner) {
      winnerDisplayed = winner;
      gameOverDisplayed = true;
    }
  }

  private ConnectFourModel model;
  private MockConnectFourView view;
  private ConnectFourController controller;


  /**
   * Set up the test environment. Create a new ConnectFourModelImpl object and a new
   * MockConnectFourView object.
   * Create a new SwingConnectFourController object with the view and model.
   */

  @Before
  public void setUp() {
    model = new ConnectFourModelImpl(4, 5);
    view = new MockConnectFourView();
    controller = new ConnectFourControllerImpl(view, model);
    view.setController(controller);
  }

  @Test
  public void testPlayGame() {
    controller.playGame(model);
    assert view.lastBoardState != null;
    assert view.playerTurnMessage != null;
  }

  @Test
  public void testMakeMove() {
    controller.playGame(model);
    controller.makeMove(0);
    assert view.lastBoardState != null;
    assert view.playerTurnMessage != null;
  }

  @Test
  public void testPlayerOneMakesMoveAndWins() {
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move

    String expectedPlayerTurnMessage = "RED Player's turn";
    assertEquals(expectedPlayerTurnMessage, view.playerTurnMessage);

    boolean expectedGameOverDisplayed = true;
    assertEquals(expectedGameOverDisplayed, view.gameOverDisplayed);

    Player expectedWinnerDisplayed = Player.RED;
    assertEquals(expectedWinnerDisplayed, view.winnerDisplayed);

    Player[][] expectedBoardState = model.getBoardState();
    assertArrayEquals(expectedBoardState, view.lastBoardState);
  }

  @Test
  public void testPlayerTwoMakesMoveAndWins() {
    controller.makeMove(4); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move

    String expectedPlayerTurnMessage = "YELLOW Player's turn";
    assertEquals(expectedPlayerTurnMessage, view.playerTurnMessage);

    boolean expectedGameOverDisplayed = true;
    assertEquals(expectedGameOverDisplayed, view.gameOverDisplayed);

    Player expectedWinnerDisplayed = Player.YELLOW;
    assertEquals(expectedWinnerDisplayed, view.winnerDisplayed);

    Player[][] expectedBoardState = model.getBoardState();
    assertArrayEquals(expectedBoardState, view.lastBoardState);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayerMakesMoveAfterGameEnds() {
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move

    controller.makeMove(1); // Yellow's move
  }


  @Test
  public void testPlayerMakesMoveAndDraw() {
    model = new ConnectFourModelImpl(4, 4);

    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(0); // Red's move
    controller.makeMove(1); // Yellow's move
    controller.makeMove(1); // Red's move
    controller.makeMove(0); // Yellow's move
    controller.makeMove(1); // Red's move
    controller.makeMove(0); // Yellow's move
    controller.makeMove(2); // Red's move
    controller.makeMove(3); // Yellow's move
    controller.makeMove(2); // Red's move
    controller.makeMove(3); // Yellow's move
    controller.makeMove(3); // Red's move
    controller.makeMove(2); // Yellow's move
    controller.makeMove(3); // Red's move
    controller.makeMove(2); // Yellow's move


    assertNull(model.getWinner());
    assertNull(view.winnerDisplayed);

    // Ensure every cell in the grid is filled
    for (int col = 0; col < 4; col++) {
      for (int row = 0; row < 4; row++) {
        assertNotNull(view.lastBoardState[row][col]);
      }
    }
  }
}
