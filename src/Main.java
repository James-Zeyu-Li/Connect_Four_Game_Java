import controller.ConnectFourController;
import controller.ConnectFourControllerImpl;
import model.ConnectFourModel;
import model.ConnectFourModelImpl;
import view.ConnectFourView;
import view.SwingConnectFourView;

/**
 * Run a Connect 4 game interactively on the console. You can make the number of rows and columns
 * configurable by passing them as command-line arguments. It is also OK to hard-code the number of
 * rows and columns to 6 and 7, respectively.
 */
public class Main {
  public static void main(String[] args) {
    int rows = 6;
    int columns = 7;
    if (args.length >= 2) {
      try {
        rows = Integer.parseInt(args[0]);
        columns = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.out.println("Invalid arguments, using default 6 rows and 7 columns.");
      }
    }

    ConnectFourModel model = new ConnectFourModelImpl(rows, columns);
    ConnectFourView view = new SwingConnectFourView("Connect 4", rows, columns);
    ConnectFourController controller = new ConnectFourControllerImpl(view, model);
    controller.playGame(model);
  }
}