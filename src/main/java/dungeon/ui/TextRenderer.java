package dungeon.ui;

import dungeon.Entity;

public class TextRenderer {
  private TextRenderer() {}

  public static void greetPlayer() {
    System.out.println("Welcome Player!");
  }

  public static void displayEntityStats(Entity entity) {
    System.out.println("Health: " + entity.getHealth());
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
