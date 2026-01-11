package dungeon.world;

import dungeon.Describable;
import dungeon.Entity;
import dungeon.Item;

import java.util.*;

public class Room implements Describable {
  private static int ROOM_COUNTER = 0;
  private final List<Entity> entities;
  private final Map<String, Item> items;
  private final Map<String, Room> exits;
  private final int id;
  private String description;

  public Room() {
    this.entities = new ArrayList<>();
    this.items = new HashMap<>();
    this.exits = new HashMap<>();
    id = createRoomId();
  }

  public Room(String description) {
    this.entities = new ArrayList<>();
    this.items = new HashMap<>();
    this.exits = new HashMap<>();
    this.description = description;
    id = createRoomId();
  }

  public static int createRoomId() {
    ROOM_COUNTER++;
    return ROOM_COUNTER;
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

  public void addItem(Item item) {
    items.put(item.getName(), item);
  }

  public boolean containsItem(String name) {
    return items.containsKey(name);
  }

  public void removeItem(Item item) {
    items.remove(item.getName());
  }

  public Item getItem(String name) {
    Item item = items.get(name);

    if (item == null) throw new NullPointerException("item is null");

    return item;
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
