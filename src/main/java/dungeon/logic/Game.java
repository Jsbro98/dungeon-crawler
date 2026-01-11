package dungeon.logic;

import dungeon.*;
import dungeon.ui.InputHandler;
import dungeon.ui.TextRenderer;
import dungeon.world.Room;

import static dungeon.logic.CommandDispatcher.ParsedCommand;

import java.util.List;
import java.util.Random;

public class Game {
  private static final Random GAME_RANDOM = new Random();
  private final Player currentPlayer;
  private final BehaviorCoordinator behaviorCoordinator;
  private final RoomRegistry roomRegistry;
  private final InputHandler userInput;
  private Room currentRoom;

  public Game(Player player) {
    this.currentPlayer = player;
    this.behaviorCoordinator = new BehaviorCoordinator();
    this.roomRegistry = new RoomRegistry();
    this.userInput = new InputHandler();
  }

  public void startGame() {
    configStart();
    while (currentPlayer.isAlive()) {
      showCommands();
      ParsedCommand parsedCommand = CommandDispatcher.dispatchCommand(userInput.getCommand());

      switch (parsedCommand.type()) {
        case EXIT -> {
          return;
        }
        case PICKUP -> playerPickup(parsedCommand.argument());
        case INVENTORY -> displayPlayerInventory();
        case ATTACK -> playerAttack();
        case MOVE -> playerMove(parsedCommand.argument());
        case HEAL -> playerHeal();
        case DIRECTIONS -> showExits();
      }
    }

    TextRenderer.displayDeathMessage();
  }

  public void generateEnemy(int numberOfEnemies) {
    for (int i = 0; i < numberOfEnemies; i++) {
      currentRoom.addEntity(new Ogre(100, 5));
    }
  }

  public void generateItems(int numberOfItems) {
    for (int i = 0; i < numberOfItems; i++) {
      currentRoom.addItem(Item.getRandomItem());
    }
  }

  public void moveCurrentRoom(String direction) {
    currentRoom = behaviorCoordinator.moveRoom(currentRoom, direction);
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

  private boolean currentRoomHasNoEntities() {
    return currentRoom.getEntities().isEmpty();
  }

  private void configStart() {
    initCurrentRoom();
    TextRenderer.greetPlayer();
    showRoomInfo();
    showExits();
  }

  // FIXME: this works fine for single word items like Item.SWORD
  //  for items such as Item.HEALING_POTION, it gets dodgy
  private void playerPickup(String argument) {
    if (currentRoom.containsItem(argument)) {
      Item item = currentRoom.getItem(argument);
      currentPlayer.pickupItem(item);
      currentRoom.removeItem(item);
    }
  }

  private void playerMove(String direction) {
    moveCurrentRoom(direction);
    if (currentRoomHasNoEntities()) {
      generateEnemy(GAME_RANDOM.nextInt(3));
    }
    generateItems(GAME_RANDOM.nextInt(2));
    showRoomInfo();
    showExits();
  }

  private void playerAttack() {
    if (!currentRoom.hasEntities()) {
      IO.println("There's nothing to attack here.");
      return;
    }

    List<Entity> entityList = currentRoom.getEntities();
    Entity target = entityList.get(GAME_RANDOM.nextInt(entityList.size()));

    if (target instanceof Combatant enemy) {
      behaviorCoordinator.handleBattle(currentPlayer, enemy);
    } else {
      currentPlayer.attack(target);
    }
  }

  private void playerHeal() {
    currentPlayer.heal(GAME_RANDOM.nextInt(25));
    IO.println(currentPlayer);
  }

  private void displayPlayerInventory() {
    TextRenderer.printPlayerInventory(currentPlayer);
  }

  private void showCommands() {
    TextRenderer.displayCommands();
  }

  private void showExits() {
    TextRenderer.showRoomExits(currentRoom);
  }

  private void showRoomInfo() {
    IO.print("The room you're currently in is ");
    TextRenderer.describe(currentRoom);

    if (currentRoom.hasEntities()) {
      IO.println("Current enemies: ");
      TextRenderer.printAllEnemies(currentRoom);
    }

    if (currentRoom.hasItems()) {
      IO.println("Items present: ");
      TextRenderer.printAllItems(currentRoom);
    }
  }
}
