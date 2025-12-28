package dungeon;

public enum Item {
  // weapons
  SWORD(ItemType.WEAPON, "Sword", 10),
  CLUB(ItemType.WEAPON, "Club", 5),

  // potions
  HEALING_POTION(ItemType.POTION, "Healing Potion", 20),
  STRENGTH_POTION(ItemType.POTION, "Strength Potion", 10);


  private final ItemType type;
  private final String name;
  private final int power;

  Item(ItemType type, String name, int power) {
    this.type = type;
    this.name = name;
    this.power = power;
  };

  // getters
  public String getName() {
    return name;
  }

  public int getPower() {
    return power;
  }

  public ItemType getType() {
    return type;
  }

  // helper enum
  public enum ItemType {
    WEAPON, POTION
  }
}