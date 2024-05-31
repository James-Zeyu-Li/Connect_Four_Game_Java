package model;

/**
 * Enum type for gamers in connect four game,
 */
public enum Player {
  RED("R"), YELLOW("Y");

  private final String displayName;

  /**
   * Constructor for the Player enum.
   *
   * @param displayName the string representation of the player
   */
  Player(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Retrieves the string representation of the player.
   *
   * @return the string representation of the player
   */
  public String getDisplayName() {
    return displayName;
  }
}

