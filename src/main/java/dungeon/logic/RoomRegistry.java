package dungeon.logic;

import dungeon.world.Room;

import java.util.HashMap;
import java.util.Map;

public class RoomRegistry {
  private final Map<Integer, Room> registry;
  private static int ROOM_COUNTER = 1;

  public RoomRegistry() {
    this.registry = new HashMap<>();
  }

  public void addRoom(Room room) {
    registry.put(ROOM_COUNTER, room);
    ROOM_COUNTER++;
  }

  public Room getRoom(int id) {
    return registry.get(id);
  }
}
