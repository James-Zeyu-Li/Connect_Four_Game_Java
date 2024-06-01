# Connect_4_game_Java
## Overview

Connect 4 is a two-player connection game in which the players take turns dropping 
colored discs into a vertically suspended grid. 
The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four discs.

## Features

- Two-player game (Red vs. Yellow)
- Simple and intuitive GUI built with Swing
- Real-time game status updates
- Error handling for invalid moves
- Game reset functionality

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA or any other Java IDE

## Installation

1. Clone the repository to your local machine:

```
git clone https://github.com/James-Zeyu-Li/Connect_Four_Game_Java.git
```

2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA).

3. Build the project to ensure all dependencies are resolved.


## Running the Game

1. Navigate to the `ConnectFourControllerImpl` class and run the `main` method, or you can build and run the JAR file.

2. Alternatively, you can build the JAR file and run it from the command line:

   Copy code

   `java -jar ConnectFourGame.jar`


## How to Play

1. The game starts with the Red player.
2. Players take turns clicking on the columns of the grid to drop their discs.
3. The game will indicate whose turn it is and will display an error message if an invalid move is attempted.
4. The first player to connect four of their discs in a row (horizontally, vertically, or diagonally) wins the game.
5. If the grid is filled without any player forming a line of four, the game ends in a draw.
6. Click the "Reset Game" button to start a new game or the "Exit Game" button to close the application.

## Project Structure

- `model`: Contains the game logic and state management.

    - `ConnectFourModel.java`: Interface defining the game model.
    - `ConnectFourModelImpl.java`: Implementation of the game model.
    - `Player.java`: Enum defining the players (RED and YELLOW).
- `view`: Contains the GUI components.

    - `ConnectFourView.java`: Interface defining the view.
    - `SwingConnectFourView.java`: Swing-based implementation of the view.
- `controller`: Contains the game controller.

    - `ConnectFourController.java`: Interface defining the controller.
    - `ConnectFourControllerImpl.java`: Implementation of the game controller.

## Contributing

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Push your branch to your forked repository.
5. Create a pull request with a description of your changes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgments

- Thanks to all the contributors and the open-source community for their support and contributions.