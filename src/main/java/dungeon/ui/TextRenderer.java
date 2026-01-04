package dungeon.ui;

import dungeon.Describable;
import dungeon.Entity;
import dungeon.world.Room;

public class TextRenderer {
  private TextRenderer() {}

  public static void greetPlayer() {
    System.out.println("Welcome Player!");
  }

  public static void describe(Describable obj) {
    IO.println(obj.describe());
  }

  public static void showRoomExits(Room room) {
    StringBuilder exitBuilder = new StringBuilder();

    for (String exit : room.getAllExits().keySet()) {
      exitBuilder.append(exit).append(", ");
    }

    IO.println("This room has exits at:");
    IO.println(exitBuilder.toString());
    IO.println();
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
