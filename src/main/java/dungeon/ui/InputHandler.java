package dungeon.ui;

import java.util.Scanner;

public class InputHandler {
  private final Scanner userIn = new Scanner(System.in);

  public String getCommand() {
    IO.print("> ");
    return userIn.nextLine().trim().toLowerCase();
  }

  // TODO: add validation to return string
  public String getMoveDirection() {
    IO.println("Which direction would you like to go?");
    TextRenderer.printDirections();
    return getCommand();
  }
}
