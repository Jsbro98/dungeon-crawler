package dungeon.ui;

public class FailureRenderer {
  public static void displayEquipFailure() {
    displayFailure("equip");
    IO.println("Item was not able to be equipped (make sure it's in your inventory)");
  }

  public static void displayPickupFailure() {
    displayFailure("pickup");
    IO.println("You cannot pick up this item (make sure it's in the room with you)");
  }

  public static void displayAttackFailure() {
    displayFailure("attack");
    IO.println("There's nothing to attack here.");
  }

  public static void displayUseFailure() {
    displayFailure("use");
    IO.println("Could not use requested item (make sure it's in your inventory)");
  }

  public static void displayItemFailure() {
    displayFailure("item selection");
    IO.println("this is not a valid item");
  }

  public static void displayInventoryFailure() {
    displayFailure("inventory selection");
    IO.println("item is not in your inventory");
  }

  public static void displayInputFailure() {
    displayFailure("command you entered");
    IO.println("the command you entered does not exist");
  }

  private static void displayFailure(String command) {
    IO.println("Could not complete " + command);
  }
}
