package dungeon.ui;

import java.util.Scanner;

public class InputHandler {
  private final Scanner userIn = new Scanner(System.in);

  public String getCommand() {
    IO.print("> ");
    return userIn.nextLine().trim().toLowerCase();
  }

  public boolean getYesNo() {
    IO.print("Would you like to continue with this action (y/n): ");
    while (true) {
      String input = userIn.nextLine().trim();

      if (input.equalsIgnoreCase("y")) return true;
      if (input.equalsIgnoreCase("n")) return false;

      IO.println("Please enter 'y' for yes, 'n' for no");
    }
  }
}
