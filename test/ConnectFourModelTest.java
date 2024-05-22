import static org.junit.Assert.assertNull;

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

}
