package dungeon.logic;

import dungeon.Player;
import dungeon.ui.TextRenderer;

public class Game {
  private final Player player;

  public Game(Player player) {
    this.player = player;
  }

  public void startGame() {
    TextRenderer.greetPlayer();
  }
}
