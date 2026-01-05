package dungeon;

import dungeon.logic.Game;
import dungeon.world.Room;

public class Main {
  static void main(String[] args) {
    Game gameLoop = new Game(new Player(100, 10));

    Room roomOne = new Room("a cold tundra with no trees for miles");
    Room roomTwo = new Room("a dense forest with trees everywhere. creatures lurk in the fog...");
    roomOne.setExit("north", roomTwo);
    roomTwo.setExit("south", roomOne);

    gameLoop.addRoom(roomOne);
    gameLoop.addRoom(roomTwo);

    gameLoop.startGame();
  }
}
