package dungeon.world;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
public enum Item {
  // weapons
  SWORD(ItemType.WEAPON, "sword", 10),
  CLUB(ItemType.WEAPON, "club", 5),

  // potions
  HEALING_POTION(ItemType.POTION, "healing potion", 20, PotionEffect.HEALING),
  STRENGTH_POTION(ItemType.POTION, "strength potion", 10, PotionEffect.STRENGTH),

  // misc types
  // using NOTHING to replace null here as cases for NOTHING will have it provide 0, leaving semantics unaffected
  NOTHING(ItemType.BLANK, "nothing", 0);

  private static final Random ITEM_RANDOM = new Random();
  private static final List<Item> OBTAINABLE_ITEMS = Arrays.stream(Item.values())
          .filter(item -> item != NOTHING)
          .toList();
  private final ItemType type;
  private final String name;
  private final PotionEffect effect;
  private final int power;


  Item(ItemType type, String name, int power) {
    this.type = type;
    this.name = name;
    this.power = power;
    this.effect = null;
  }

  Item(ItemType type, String name, int power, PotionEffect effect) {
    this.type = type;
    this.name = name;
    this.power = power;
    this.effect = effect;
  }

  public static Item getRandomItem() {
    int index = ITEM_RANDOM.nextInt(OBTAINABLE_ITEMS.size());
    return OBTAINABLE_ITEMS.get(index);
  }

  // getters
  public String getName() {
    return name;
  }

  public PotionEffect getEffect() {
    if (type == ItemType.POTION && effect == null) {
      throw new IllegalStateException("Potion must have an effect");
    }
    return effect;
  }

  public int getPower() {
    return power;
  }

  public ItemType getType() {
    return type;
  }

  public boolean isNothing() {
    return type == ItemType.BLANK;
  }

  // helper enum
  public enum ItemType {
    WEAPON, POTION, BLANK
  }

  public enum PotionEffect {
    HEALING, STRENGTH
  }
}