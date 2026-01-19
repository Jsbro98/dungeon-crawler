package dungeon.ui;

import dungeon.world.*;

public class TextRenderer {
  private TextRenderer() {
  }

  public static void greetPlayer() {
    System.out.println("Welcome Player!");
  }

  public static void describe(Describable obj) {
    IO.println(obj.describe());
    IO.println();
  }

  public static void showRoomExits(Room room) {
    var exits = room.getAllExits();
    if (exits.isEmpty()) return;

    IO.println("This room has exits at:");
    exits.forEach((direction, destRoom) ->
            IO.println("  " + direction + " -> Room " + destRoom.getId())
    );
    IO.println();
  }

  public static void printAllEnemies(Room room) {
    if (room.hasEntities()) {
      for (Entity entity : room.getEntities()) {
        if (entity instanceof Player) continue;
        IO.println(entity);
      }
    }
    IO.println();
  }

  public static void printAllItems(Room room) {
    if (room.hasItems()) {
      for (Item item : room.getItems()) {
        IO.println(item);
      }
    }
    IO.println();
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
    IO.println();
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

  public static void displayEntityStats(Entity entity) {
    System.out.println("Health: " + entity.getHealth());
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
