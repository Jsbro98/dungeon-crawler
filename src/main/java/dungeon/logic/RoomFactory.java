package dungeon.logic;

import dungeon.world.Room;

import java.util.List;
import java.util.Random;

public class RoomFactory {
  private static final Random ROOM_RANDOM = new Random();
  private static final List<String> ROOM_DESCRIPTIONS = List.of(
    "a cold tundra with no trees for miles",
    "a dense forest with trees everywhere. creatures lurk in the fog...",
    "a urban settlement with tall spires and forgotten ruins",
    "a frozen lake with a treeline surrounding you",
    "a flat, volcanic plain at the base of the active mt. saxum",
    "a lush, sprawling jungle, where insects and greenery run rampant",
    "a canyon on unyielding awe. miles of chasms and formations litter the landscape",
    "an Ogre village with tribal elements scattered among the huts",
    "a cave with glowing cracks showing through the immense walls");

  private RoomFactory() {}

  public static Room createRoomWithDescription() {
    return new Room(getRandomDescription());
  }

  public static Room createBossRoom() {
    return new Room("a massive spire surrounded by gold jewels. an unnerving, hulking presence lurks in the shadows");
  }

  public static Room createRoom() {
    return new Room();
  }

  private static String getRandomDescription() {
    return ROOM_DESCRIPTIONS.get(ROOM_RANDOM.nextInt(ROOM_DESCRIPTIONS.size()));
  }
}
