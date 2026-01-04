package dungeon.logic;

import dungeon.Player;
import dungeon.ui.TextRenderer;

public class Game {
  private final Player player;
  private final Turn turn;
  private final RoomRegistry roomRegistry;

  public Game(Player player) {
    this.player = player;
    this.turn = new Turn();
    this.roomRegistry = new RoomRegistry();
  }

  public void startGame() {
    TextRenderer.greetPlayer();
  }
}
