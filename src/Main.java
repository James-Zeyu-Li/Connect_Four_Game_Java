import javax.swing.SwingUtilities;
import view.SwingConnectFourView;

/**
 * Run a Connect 4 game interactively on the console. You can make the number of rows and columns
 * configurable by passing them as command-line arguments. It is also OK to hard-code the number of
 * rows and columns to 6 and 7, respectively.
 */
public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      new SwingConnectFourView("Connect Four");
    });
  }
}