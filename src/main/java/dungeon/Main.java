package dungeon;

import dungeon.logic.Game;
import dungeon.logic.RoomFactory;
import dungeon.world.Room;

public class Main {
  static void main(String[] args) {
    Game gameLoop = new Game(new Player(100, 10));

    Room roomOne = RoomFactory.createRoomWithDescription();
    Room roomTwo = RoomFactory.createRoomWithDescription();
    roomOne.setExit("north", roomTwo);
    roomTwo.setExit("south", roomOne);

    gameLoop.addRoom(roomOne);
    gameLoop.addRoom(roomTwo);

    gameLoop.startGame();
  }
}
