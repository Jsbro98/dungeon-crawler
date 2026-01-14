package dungeon.logic;

import dungeon.Item;

import java.util.Arrays;
import java.util.List;

public class ItemValidator {
  public Item validateItem(String itemName) {
    itemName = itemName.trim().toLowerCase();
    // TODO: continue here. Have itemName switch on all types
    //  from the Item enum, check the name, then return the Item
  }

  private Item.ItemType getTypeOfItem(String name) {
    List<String> parts = Arrays.stream(name.split(" ")).toList();
    if (parts.contains("potion")) {
      return Item.ItemType.POTION;
    }

    if (isStringAnItem(name)) {
      return Item.ItemType.WEAPON;
    }

    return Item.ItemType.BLANK;
  }

  private boolean isStringAnItem(String arg) {
    for (Item item : Item.values()) {
      String itemName = item.getName().toLowerCase();
      if (arg.equals(itemName)) {
        return true;
      }
    }

    return false;
  }
}
