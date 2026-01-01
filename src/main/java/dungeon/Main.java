package dungeon;

import dungeon.logic.Turn;
import dungeon.ui.TextRenderer;
import dungeon.world.Room;

public class Main {
  static void main(String[] args) {
    Room roomOne = new Room();
    roomOne.setDescription("A cold tundra with no trees for miles");
    Room roomTwo = new Room();
    roomTwo.setDescription("A dense forest with trees everywhere. creatures lurk in the fog...");
    Room currentRoom = roomOne;

    roomOne.setExit("north", roomTwo);
    roomTwo.setExit("south", roomOne);

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
    currentRoom = turn.moveRoom(roomOne, "north");
    IO.print("Current Room: ");
    TextRenderer.describe(roomTwo);
    if (currentRoom == roomTwo && roomTwo.containsEntity(ogre)) {
      turn.handleBattle(player, ogre);
    }

    turn = new Turn();
    player.heal(25);
    IO.println(player);
  }
}
