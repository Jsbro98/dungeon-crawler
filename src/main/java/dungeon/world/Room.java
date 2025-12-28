package dungeon.world;

import dungeon.Entity;
import java.util.ArrayList;
import java.util.List;

public class Room {
  private final List<Entity> entities;

  public Room() {
    this.entities = new ArrayList<>();
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
