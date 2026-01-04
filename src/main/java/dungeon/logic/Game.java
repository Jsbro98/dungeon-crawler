package dungeon.logic;

import dungeon.Player;
import dungeon.ui.TextRenderer;
import dungeon.world.Room;

public class Game {
  private final Player player;
  private final Turn turn;
  private final RoomRegistry roomRegistry;
  private Room currentRoom;

  public Game(Player player) {
    this.player = player;
    this.turn = new Turn();
    this.roomRegistry = new RoomRegistry();
  }

  public void startGame() {
    TextRenderer.greetPlayer();
  }

  // TODO: change this delegation to make more sense
  public void moveToRoom(String direction) {
    currentRoom = turn.moveRoom(currentRoom, direction);
  }
}
