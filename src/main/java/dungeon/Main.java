package dungeon;

import dungeon.logic.Game;
import dungeon.logic.RoomFactory;
import dungeon.world.Player;
import dungeon.world.Room;

public class Main {
  static void main(String[] args) {
    Game gameLoop = new Game(new Player(100, 10));

    Room roomOne = RoomFactory.createRoomWithDescription();
    Room roomTwo = RoomFactory.createRoomWithDescription();
    Room roomThree = RoomFactory.createRoomWithDescription();
    Room roomFour = RoomFactory.createRoomWithDescription();
    Room bossRoom = RoomFactory.createBossRoom();

    roomOne.setExit("north", roomTwo);

    roomTwo.setExit("south", roomOne);
    roomTwo.setExit("east", bossRoom);
    roomTwo.setExit("west", roomThree);

    roomThree.setExit("east", roomTwo);
    roomThree.setExit("south", roomFour);

    roomFour.setExit("north", roomThree);
    roomFour.setExit("west", roomOne);

    gameLoop.addRoom(roomOne);
    gameLoop.addRoom(roomTwo);
    gameLoop.addRoom(roomThree);
    gameLoop.addRoom(roomFour);
    gameLoop.addRoom(bossRoom);

    gameLoop.startGame();
  }
}
