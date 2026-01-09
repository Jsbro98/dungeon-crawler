package dungeon.ui;

import java.util.Scanner;

public class InputHandler {
  private final Scanner userIn = new Scanner(System.in);

  public String getCommand() {
    IO.print("> ");
    return userIn.nextLine().trim().toLowerCase();
  }
}
