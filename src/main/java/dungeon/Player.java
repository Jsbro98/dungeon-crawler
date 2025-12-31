package dungeon;

import java.util.Map;
import java.util.Random;

public class Player extends Entity implements Attacker {
  // Player damage variance is set to 3 (0-3)
  public static final int MAX_VARIANCE = 4;
  private static final Random PLAYER_RANDOM = new Random();
  private final Inventory inventory;
  private final int strength;
  // possibly make a class/record for EquipmentSlot?
  private Item equipped;

  public Player(int health, int strength) {
    super(health);
    this.inventory = new Inventory(5);
    this.strength = strength;
    this.equipped = Item.NOTHING;
  }

  public void pickupItem(Item item) {
    inventory.addToInventory(item.getName(), item);
  }

  public void dropItem(Item item) {
    inventory.removeFromInventory(item.getName());
    if (equipped == item) {
      equipped = Item.NOTHING;
    }
  }

  public Map<String, Item> viewInventory() {
    return inventory.getInventory();
  }

  // this can return null!
  public Item getFromInventory(Item item) {
    return inventory.getInventory().get(item.getName());
  }

  public void equipItem(Item item) {
    Item targetItem = getFromInventory(item);
    if (targetItem == null || targetItem.isNothing()) return;
    equipped = item;
  }

  public Item getEquipped() {
    return equipped;
  }

  public boolean hasItemEquipped() {
    return equipped != Item.NOTHING;
  }

  @Override
  public String toString() {
    return "Player stats: health-(" + getHealth() + ")";
  }

  @Override
  public int getDamage() {
    int baseDamage = strength;

    if (hasItemEquipped()) {
      baseDamage += equipped.getPower();
    }

    return baseDamage + (PLAYER_RANDOM.nextInt(getDamageVariance()));
  }

  @Override
  public int getDamageVariance() {
    return MAX_VARIANCE;
  }

  @Override
  public void attack(Entity other) {
    other.takeDamage(this.getDamage());
  }
}
