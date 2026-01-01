package dungeon.world;

import dungeon.Entity;

import java.util.*;

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

  public boolean containsEntity(Entity entity) {
    return entities.contains(entity);
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

  public Map<String, Room> getAllExits() {
    return Collections.unmodifiableMap(exits);
  }

  @Override
  public String toString() {
    return "Room{" +
            "entities=" + entities +
            ", exits=" + exits +
            '}';
  }
}
