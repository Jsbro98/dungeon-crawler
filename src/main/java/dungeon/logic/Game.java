package dungeon.logic;

import dungeon.Combatant;
import dungeon.Entity;
import dungeon.Ogre;
import dungeon.Player;
import dungeon.ui.InputHandler;
import dungeon.ui.TextRenderer;
import dungeon.world.Room;

import java.util.List;
import java.util.Random;

public class Game {
  private static final Random GAME_RANDOM = new Random();
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
    configStart();
    while (currentPlayer.isAlive()) {
      showCommands();
      String command = userInput.getCommand();

      if (command.equals("exit")) break;

      if (command.startsWith("go ")) {
        playerMove(command);
      }

      if (command.equals("heal")) {
        currentPlayer.heal(GAME_RANDOM.nextInt(25));
        IO.println(currentPlayer);
      }

      if (command.equals("attack")) {
        playerAttack();
      }

      if (command.equals("directions")) {
        showExits();
      }

      if (command.equals("inventory")) {
        displayPlayerInventory();
      }
    }
  }

  private void configStart() {
    initCurrentRoom();
    TextRenderer.greetPlayer();
    showRoomInfo();
    showExits();
  }

  public void playerMove(String command) {
    String direction = command.substring(3);
    moveCurrentRoom(direction);
    generateEnemy(5);
    showRoomInfo();
    showExits();
  }

  public void playerAttack() {
    if (currentRoom.hasEntities()) {
      List<Entity> entityList = currentRoom.getEntities();
      Entity target = entityList.get(GAME_RANDOM.nextInt(entityList.size()));

      if (target instanceof Combatant enemy) {
        turn.handleBattle(currentPlayer, enemy);
      } else {
        currentPlayer.attack(target);
      }
    }
  }

  public void displayPlayerInventory() {
    TextRenderer.printPlayerInventory(currentPlayer);
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
  }
}
