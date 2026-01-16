package dungeon.logic;

import dungeon.ui.InputHandler;
import dungeon.ui.TextRenderer;
import dungeon.world.*;

import java.util.List;
import java.util.Random;

import static dungeon.logic.CommandDispatcher.ParsedCommand;

public class Game {
  private static final Random GAME_RANDOM = new Random();
  private final Player currentPlayer;
  private final BehaviorCoordinator behaviorCoordinator;
  private final RoomRegistry roomRegistry;
  private final InputHandler userInput;
  private Room currentRoom;
  private boolean didPlayerWin;

  public Game(Player player) {
    this.currentPlayer = player;
    this.behaviorCoordinator = new BehaviorCoordinator();
    this.roomRegistry = new RoomRegistry();
    this.userInput = new InputHandler();
    this.didPlayerWin = false;
  }

  // main entry method for the game
  public void startGame() {
    configStart();
    while (currentPlayer.isAlive() && !didPlayerWin) {
      showCommands();
      ParsedCommand parsedCommand = CommandDispatcher.dispatchCommand(userInput.getCommand());

      switch (parsedCommand.type()) {
        case EXIT -> {
          return;
        }
        case EQUIP -> playerEquip(parsedCommand.argument());
        case PICKUP -> playerPickup(parsedCommand.argument());
        case INVENTORY -> displayPlayerInventory();
        case ATTACK -> playerAttack();
        case MOVE -> playerMove(parsedCommand.argument());
        case DIRECTIONS -> showExits();
        case USE -> playerUse(parsedCommand.argument());
      }
    }

    if (didPlayerWin) {
      TextRenderer.displayVictoryMessage();
      return;
    }

    TextRenderer.displayDeathMessage();
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

  private void setPlayerWon() {
    didPlayerWin = true;
  }

  private void generateEnemy(int numberOfEnemies) {
    for (int i = 0; i < numberOfEnemies; i++) {
      currentRoom.addEntity(new Ogre(100, 5));
    }
  }

  private void generateBossEnemy() {
    currentRoom.addEntity(new Ogre(300, 10));
  }

  private void generateItems(int numberOfItems) {
    for (int i = 0; i < numberOfItems; i++) {
      currentRoom.addItem(Item.getRandomItem());
    }
  }

  private void moveCurrentRoom(String direction) {
    currentRoom = behaviorCoordinator.moveRoom(currentRoom, direction);
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

  private void playerPickup(String argument) {
    if (!currentRoom.containsItem(argument)) {
      TextRenderer.displayPickupFailure();
      return;
    }

    Item item = currentRoom.getItem(argument);
    currentPlayer.pickupItem(item);
    currentRoom.removeItem(item);
  }

  private void playerEquip(String itemName) {
    Item item = ItemValidator.validateItem(itemName)
            .orElseThrow(() -> new NullPointerException("Item requested was null"));

    if (currentPlayer.doesNotOwn(item)) {
      TextRenderer.displayEquipFailure();
      return;
    }

    currentPlayer.equipItem(currentPlayer.getFromInventory(item));
  }

  private void playerMove(String direction) {
    moveCurrentRoom(direction);

    if (playerEnteredBossRoom()) return;

    if (currentRoomHasNoEntities()) {
      generateEnemy(GAME_RANDOM.nextInt(3));
    }
    generateItems(GAME_RANDOM.nextInt(2));
    showRoomInfo();
    showExits();
  }

  private boolean playerEnteredBossRoom() {
    if (currentRoom.isBossRoom()) {
      generateBossEnemy();
      behaviorCoordinator.handleBossBattle(currentPlayer, currentRoom);
      if (currentPlayer.isAlive()) setPlayerWon();
      return true;
    }
    return false;
  }

  private void playerAttack() {
    if (!currentRoom.hasEntities()) {
      TextRenderer.displayAttackFailure();
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

  private void playerUse(String arg) {
    Item item = ItemValidator.validateItem(arg)
            .orElseThrow(() -> new NullPointerException("Item requested was null"));

    if (currentPlayer.doesNotOwn(item)) {
      TextRenderer.displayUseFailure();
      return;
    }

    currentPlayer.usePotion(item);
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
