package dungeon;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
  // maybe create Inventory class?
  private final Map<String, Item> inventory;

  public Player(int health) {
    super(health);
    this.inventory = new HashMap<>();
  }

  @Override
  public String toString() {
    return "Player stats: health-(" + getHealth() + ")";
  }
}
