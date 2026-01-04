package dungeon.ui;

import dungeon.Describable;
import dungeon.Entity;
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

  public static void displayCommands() {
    IO.println("""
            Commands:
            1. go <direction>
            2. attack
            3. exit
            """);
  }

  public static void printDirections() {
    IO.println("""
            Which direction do you travel?
            north, south, east, or west?
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
