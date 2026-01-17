package dungeon.world;

import dungeon.logic.Inventory;
import dungeon.ui.TextRenderer;

import java.util.Map;
import java.util.Random;

public class Player extends Combatant {
  // Player damage variance is set to 3 (0-3)
  public static final int MAX_VARIANCE = 4;
  private static final Random PLAYER_RANDOM = new Random();
  private final Inventory inventory;
  private final int strength;
  // possibly make a class/record for EquipmentSlot?
  private Item equipped;
  // TODO: add Attributes object for this
  private int strengthBoost;
  private int strengthBoostTurns;

  public Player(int health, int strength) {
    super(health);
    this.inventory = new Inventory(5);
    this.strength = strength;
    this.equipped = Item.NOTHING;
    this.strengthBoost = 0;
    this.strengthBoostTurns = 0;
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

  public Item getFromInventory(Item item) {
    Item wantedItem = inventory.getInventory().get(item.getName());

    if (wantedItem == null || wantedItem.isNothing()) {
      throw new NullPointerException("wanted item was null or NOTHING");
    }

    return wantedItem;
  }

  public void equipItem(Item item) {
    Item targetItem = getFromInventory(item);
    if (targetItem == null || targetItem.isNothing()) return;
    equipped = item;
  }

  public void usePotion(Item item) {
    if (item.getType() != Item.ItemType.POTION) {
      TextRenderer.displayUseFailure();
      return;
    }

    if (doesNotOwn(item)) {
      TextRenderer.displayInventoryFailure();
      return;
    }

    switch (item.getEffect()) {
      case HEALING -> heal(item.getPower());
      case STRENGTH -> addDamage(item.getPower(), 5);
    }

    inventory.removeFromInventory(item.getName());
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

    if (hasStrengthBoost()) {
      baseDamage += strengthBoost;
      strengthBoostTurns--;
    }

    return baseDamage + (PLAYER_RANDOM.nextInt(getDamageVariance()));
  }

  @Override
  public int getDamageVariance() {
    return MAX_VARIANCE;
  }

  public boolean doesNotOwn(Item item) {
    return !viewInventory().containsValue(item);
  }

  private void addDamage(int power, int numberOfTurns) {
    strengthBoost = power;
    strengthBoostTurns = numberOfTurns;
  }

  private boolean hasStrengthBoost() {
    return strengthBoostTurns > 0;
  }
}
