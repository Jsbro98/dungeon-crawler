package dungeon;

import dungeon.logic.Turn;
import dungeon.world.Room;

public class Main {
  static void main(String[] args) {
    Room roomOne = new Room();
    Room roomTwo = new Room();
    Room currentRoom = roomOne;

    roomOne.setExit("north", roomTwo);
    roomTwo.setExit("south", roomOne);

    Player player = new Player(100, 5);
    Ogre ogre = new Ogre(200, 10);
    roomTwo.addEntity(ogre);
    roomOne.addEntity(player);

    player.pickupItem(Item.SWORD);
    player.equipItem(Item.SWORD);

    Turn turn = new Turn();
    currentRoom = turn.moveRoom(roomOne, "north");
    if (currentRoom == roomTwo && roomTwo.containsEntity(ogre)) {
      turn.handleBattle(player, ogre);
    }
  }
}
