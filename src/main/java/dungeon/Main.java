package dungeon;

import dungeon.logic.Game;
import dungeon.logic.RoomFactory;
import dungeon.world.Room;

public class Main {
  static void main(String[] args) {
    Game gameLoop = new Game(new Player(100, 10));

    Room roomOne = RoomFactory.createRoomWithDescription();
    Room roomTwo = RoomFactory.createRoomWithDescription();
    Room bossRoom = RoomFactory.createBossRoom();
    roomOne.setExit("north", roomTwo);
    roomTwo.setExit("south", roomOne);
    roomTwo.setExit("east", bossRoom);

    gameLoop.addRoom(roomOne);
    gameLoop.addRoom(roomTwo);
    gameLoop.addRoom(bossRoom);

    gameLoop.startGame();
  }
}
