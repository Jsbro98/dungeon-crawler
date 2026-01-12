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
  private boolean isBossRoom;

  public Room() {
    this.entities = new ArrayList<>();
    this.items = new HashMap<>();
    this.exits = new HashMap<>();
    this.id = createRoomId();
    this.isBossRoom = false;
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

  public boolean hasItems() {
    return !items.isEmpty();
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

  // FIXME: code smell on the toLowerCase. Read below
  public void addItem(Item item) {
    items.put(item.getName().toLowerCase(), item);
  }

  public boolean containsItem(String name) {
    return items.containsKey(name);
  }

  // FIXME: item adding and removal relies on String.toLowerCase
  //  look into a fix for it
  public void removeItem(Item item) {
    items.remove(item.getName().toLowerCase());
  }

  public Item getItem(String name) {
    Item item = items.get(name);

    if (item == null) throw new NullPointerException("item is null");

    return item;
  }


  public void setExit(String exitDirection, Room room) {
    exits.put(exitDirection, room);
  }

  public void setThisToBossRoom() {
    isBossRoom = true;
  }

  public boolean isBossRoom() {
    return isBossRoom;
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

  public List<Item> getItems() {
    return items.values().stream().toList();
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
