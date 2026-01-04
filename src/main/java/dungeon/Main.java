package dungeon;

import dungeon.logic.Game;
import dungeon.logic.Turn;
import dungeon.ui.TextRenderer;
import dungeon.world.Room;

public class Main {
  static void main(String[] args) {
    Game gameLoop = new Game(new Player(100, 10));

    Room roomOne = new Room("A cold tundra with no trees for miles");
    Room roomTwo = new Room("A dense forest with trees everywhere. creatures lurk in the fog...");
    roomOne.setExit("north", roomTwo);
    roomTwo.setExit("south", roomOne);

    gameLoop.getRoomRegistry().addRoom(roomOne);
    gameLoop.getRoomRegistry().addRoom(roomTwo);
    gameLoop.initCurrentRoom();


    Player player = new Player(100, 10);
    Ogre ogre = new Ogre(100, 10);
    roomTwo.addEntity(ogre);
    roomOne.addEntity(player);

    TextRenderer.greetPlayer();
    IO.print("Current Room: ");
    TextRenderer.describe(roomOne);

    player.pickupItem(Item.SWORD);
    player.equipItem(Item.SWORD);

    Turn turn = new Turn();
    gameLoop.moveToRoom("north");
    IO.print("Current Room: ");
    TextRenderer.describe(gameLoop.getCurrentRoom());
    if (gameLoop.getCurrentRoom() == roomTwo && roomTwo.containsEntity(ogre)) {
      turn.handleBattle(player, ogre);
    }

    player.heal(25);
    IO.println(player);
  }
}
