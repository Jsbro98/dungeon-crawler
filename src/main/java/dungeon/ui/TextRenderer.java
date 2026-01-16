package dungeon.ui;

import dungeon.Describable;
import dungeon.Entity;
import dungeon.Item;
import dungeon.Player;
import dungeon.world.Room;

import java.util.ArrayList;

public class TextRenderer {
  private TextRenderer() {}

  public static void greetPlayer() {
    System.out.println("Welcome Player!");
  }

  public static void describe(Describable obj) {
    IO.println(obj.describe());
  }

  public static void showRoomExits(Room room) {
    ArrayList<String> exits = new ArrayList<>(room.getAllExits().keySet());
    StringBuilder exitBuilder = new StringBuilder();

    for (String exit : exits) {
      if (exit.equals(exits.getLast())) {
        exitBuilder.append(exit);
        continue;
      }

      exitBuilder.append(exit).append(", ");
    }

    IO.println("This room has exits at:");
    IO.println(exitBuilder.toString());
    IO.println();
  }

  public static void printAllEnemies(Room room) {
    if (room.hasEntities()) {
      for (Entity entity : room.getEntities()) {
        if (entity instanceof Player) continue;
        IO.println(entity);
      }
    }
  }

  public static void printAllItems(Room room) {
    if (room.hasItems()) {
      for (Item item : room.getItems()) {
        IO.println(item);
      }
    }
  }

  public static void displayDeathMessage() {
    IO.println("You died! Game Over.\n" +
            "Thanks for playing.");
  }

  public static void displayCommands() {
    IO.print("""
            Commands:
            1. go <direction>
            2. attack
            3. inventory
            4. directions
            5. pickup <item>
            6. equip <item>
            7. use <potion type>
            8. exit
            """);
  }

  public static void printPlayerInventory(Player player) {
    for (Item item : player.viewInventory().values()) {
      IO.println("Item: " + item);
    }
  }

  public static void printDirections() {
    IO.println("""
            Which direction do you travel?
            north, south, east, or west?
            """);
  }

  public static void printEnteringBossRoomMessage() {
    IO.println("""
            You've entered the final room... there is no exit... either victory or death!
            """);
  }

  public static void displayVictoryMessage() {
    IO.println("""
            You've defeated the Boss! You're victorious!
            """);
  }

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

  public static void displayEntityStats(Entity entity) {
    System.out.println("Health: " + entity.getHealth());
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private static void displayFailure(String command) {
    IO.println("Could not complete " + command);
  }
}
