package dungeon.logic;

import dungeon.world.Item;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemValidator {
  private static final Map<String, Item> ITEM_REGISTRY =
          Arrays.stream(Item.values())
                  .filter(item -> !item.isNothing())
                  .collect(Collectors.toMap(
                          item -> item.getName().toLowerCase(),
                          item -> item
                  ));

  public static Optional<Item> validateItem(String itemName) {
    itemName = itemName.trim().toLowerCase();
    return Optional.ofNullable(ITEM_REGISTRY.get(itemName));
  }
}
