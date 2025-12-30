package dungeon.world;

import dungeon.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
  private final List<Entity> entities;
  private final Map<String, Room> exits;

  public Room() {
    this.entities = new ArrayList<>();
    this.exits = new HashMap<>();
  }

  public boolean hasEntities() {
    return !entities.isEmpty();
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }

  public void removeEntity(Entity entity) {
    entities.remove(entity);
  }

  public void setExit(String exitDirection, Room room) {
    exits.put(exitDirection, room);
  }

  public Room getExit(String direction) {
    direction = direction.trim().toLowerCase();

    if (exits.containsKey(direction)) {
      return exits.get(direction);
    }

    throw new IllegalArgumentException("Key provided for exit does not exist");
  }

  @Override
  public String toString() {
    return "Room{" +
            "entities=" + entities +
            ", exits=" + exits +
            '}';
  }
}
