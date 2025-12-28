package dungeon.world;

import dungeon.Entity;
import java.util.ArrayList;

public class Room {
  private final ArrayList<Entity> entities;

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
}
