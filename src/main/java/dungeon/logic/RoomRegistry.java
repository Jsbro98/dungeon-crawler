package dungeon.logic;

import dungeon.world.Room;

import java.util.HashMap;
import java.util.Map;

public class RoomRegistry {
  private final Map<Integer, Room> registry;
  private static int ROOM_COUNTER = 0;

  public RoomRegistry() {
    this.registry = new HashMap<>();
  }

  public static int createRoomId() {
    ROOM_COUNTER++;
    return ROOM_COUNTER;
  }

  public void register(Room room) {
    registry.put(room.getId(), room);
  }

  public Room getRoom(int id) {
    return registry.get(id);
  }
}
