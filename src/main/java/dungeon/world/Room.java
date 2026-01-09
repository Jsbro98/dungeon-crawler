package dungeon.world;

import dungeon.Describable;
import dungeon.Entity;
import dungeon.logic.RoomRegistry;

import java.util.*;

public class Room implements Describable {
  private final List<Entity> entities;
  private final Map<String, Room> exits;
  private final int id;
  private String description;

  public Room() {
    this.entities = new ArrayList<>();
    this.exits = new HashMap<>();
    id = RoomRegistry.createRoomId();
  }

  public Room(String description) {
    this.entities = new ArrayList<>();
    this.exits = new HashMap<>();
    this.description = description;
    id = RoomRegistry.createRoomId();
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
    if (exits.containsKey(direction)) {
      return exits.get(direction);
    }

    throw new IllegalArgumentException("Key provided for exit does not exist");
  }

  public Map<String, Room> getAllExits() {
    return Collections.unmodifiableMap(exits);
  }

  public int getId() {
    return id;
  }

  public List<Entity> getEntities() {
    return Collections.unmodifiableList(entities);
  }

  @Override
  public String toString() {
    return "Room{" + "entities=" + entities + ", exits=" + exits.keySet() + '}';
  }

  @Override
  public String describe() {
    return description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }
}
