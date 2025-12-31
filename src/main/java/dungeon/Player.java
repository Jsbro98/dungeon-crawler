package dungeon;

import java.util.Map;
import java.util.Random;

public class Player extends Entity implements Attacker {
  private final Inventory inventory;
  private final int strength;
  // possibly make a class/record for EquipmentSlot?
  private Item equipped;

  public Player(int health, int strength) {
    super(health);
    this.inventory = new Inventory(2);
    this.strength = strength;
    this.equipped = Item.NOTHING;
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

  public void equipItem(Item item) {
    equipped = item;
  }

  public Item getEquiped() {
    return equipped;
  }

  @Override
  public String toString() {
    return "Player stats: health-(" + getHealth() + ")";
  }

  @Override
  public int getDamage() {
    Random random = new Random();
    int maxVariance = 3;
    int baseDamage = strength;
    Item currentEquipped = getEquiped();

    if (currentEquipped != Item.NOTHING) {
      baseDamage += currentEquipped.getPower();
    }

    return baseDamage + (random.nextInt(maxVariance));
  }

  @Override
  public void attack(Entity other) {
    other.takeDamage(this.getDamage());
  }
}
