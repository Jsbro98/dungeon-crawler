package dungeon.logic;

import dungeon.world.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomFactory {
  private static final Random ROOM_RANDOM = new Random();
  private static final List<String> ROOM_DESCRIPTIONS = new ArrayList<>() {{
    add("a cold tundra with no trees for miles");
    add("a dense forest with trees everywhere. creatures lurk in the fog...");
    add("a urban settlement with tall spires and forgotten ruins");
    add("a frozen lake with a treeline surrounding you");
    add("a flat, volcanic plain at the base of the active mt. saxum");
    add("a lush, sprawling jungle, where insects and greenery run rampant");
    add("a canyon on unyielding awe. miles of chasms and formations litter the landscape");
    add("an Ogre village with tribal elements scattered among the huts");
    add("a cave with glowing cracks showing through the immense walls");
  }};

  public static Room createRoomWithDescription() {
    return new Room(getRandomDescription());
  }

  public static Room createRoom() {
    return new Room();
  }

  private static String getRandomDescription() {
    return ROOM_DESCRIPTIONS.get(ROOM_RANDOM.nextInt(ROOM_DESCRIPTIONS.size()));
  }
}
