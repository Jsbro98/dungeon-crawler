package dungeon.world;

import dungeon.Entity;
import dungeon.Item;

import java.util.ArrayList;
import java.util.List;

public class Room {
  private final List<Entity> entities;
  private final List<Item> items;

  public Room() {
    this.entities = new ArrayList<>();
    this.items = new ArrayList<>();
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

  @Override
  public String toString() {
    return "Room{" +
            "entities=" + entities +
            '}';
  }
}
