package dungeon;

import java.util.Map;

public class Player extends Entity {
  private final Inventory inventory;

  public Player(int health) {
    super(health);
    this.inventory = new Inventory(2);
  }

  public void pickupItem(Item item) {
    inventory.addToInventory(item.getName(), item);
  }

  public void dropItem(Item item) {
    inventory.removeFromInventory(item.getName());
  }

  public Map<String, Item> viewInventory() {
    return inventory.getInventory();
  }

  @Override
  public String toString() {
    return "Player stats: health-(" + getHealth() + ")";
  }
}
