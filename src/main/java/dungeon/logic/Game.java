package dungeon.logic;

import dungeon.Player;
import dungeon.ui.TextRenderer;
import dungeon.world.Room;

public class Game {
  private final Player currentPlayer;
  private final Turn turn;
  private final RoomRegistry roomRegistry;
  private Room currentRoom;

  public Game(Player player) {
    this.currentPlayer = player;
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

  public void addRoom(Room room) {
    roomRegistry.register(room);
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void initCurrentRoom() {
    currentRoom = roomRegistry.getRoom(1);
    currentRoom.addEntity(getCurrentPlayer());
  }
}
