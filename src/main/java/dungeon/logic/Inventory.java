package dungeon.logic;

import dungeon.world.Item;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
  private final int inventorySize;
  private final Map<String, Item> inventory;

  public Inventory(int inventorySize) {
    this.inventorySize = inventorySize;
    this.inventory = new HashMap<>();
  }

  public void addToInventory(String key, Item item) {
    if (inventory.size() >= inventorySize)
      throw new UnsupportedOperationException("could not pick up " + item.getName() + ", inventory is full");

    if (inventory.containsKey(key))
      throw new UnsupportedOperationException("duplicate item provided for " + item.getName() + ", duplicates are not allowed");

    inventory.put(key, item);
  }

  public void removeFromInventory(String key) {
    inventory.remove(key);
  }

  // provide a defensive copy for get operations
  public Map<String, Item> getInventory() {
    return Collections.unmodifiableMap(inventory);
  }

  @Override
  public String toString() {
    return "Inventory{" + "inventory=" + inventory + '}';
  }
}
