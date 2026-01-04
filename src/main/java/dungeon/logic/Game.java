package dungeon.logic;

import dungeon.Entity;
import dungeon.Ogre;
import dungeon.Player;
import dungeon.ui.InputHandler;
import dungeon.ui.TextRenderer;
import dungeon.world.Room;

public class Game {
  private final Player currentPlayer;
  private final Turn turn;
  private final RoomRegistry roomRegistry;
  private final InputHandler userInput;
  private Room currentRoom;

  public Game(Player player) {
    this.currentPlayer = player;
    this.turn = new Turn();
    this.roomRegistry = new RoomRegistry();
    this.userInput = new InputHandler();
  }

  public void startGame() {
    initCurrentRoom();

    TextRenderer.greetPlayer();
    showRoomInfo();
    showExits();
    while (currentPlayer.isAlive()) {
      showCommands();
      String command = userInput.getCommand();

      if (command.equals("exit")) break;

      if (command.startsWith("go ")) {
        String direction = command.substring(3);
        moveCurrentRoom(direction);
        generateEnemy(5);
        showRoomInfo();
        showExits();
      }
    }
  }

  public void showCommands() {
    TextRenderer.displayCommands();
  }

  public void showExits() {
    TextRenderer.showRoomExits(currentRoom);
  }

  public void showRoomInfo() {
    IO.print("The room you're currently in is ");
    TextRenderer.describe(currentRoom);

    if (currentRoom.hasEntities()) {
      IO.println("Current enemies: ");
      TextRenderer.printAllEnemies(currentRoom);
    }
  }

  public void generateEnemy(int numberOfEnemies) {
    for (int i = 0; i < numberOfEnemies; i++) {
      currentRoom.addEntity(new Ogre(100, 5));
    }
  }

  public void moveCurrentRoom(String direction) {
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
