package dungeon.ui;

import java.util.Scanner;

public class InputHandler {
  private final Scanner userIn = new Scanner(System.in);

  // TODO: add validation to return string
  public String getDesiredMoveDirection() {
    IO.println("Which direction would you like to go?");
    TextRenderer.printDirections();

    return userIn.nextLine();
  }
}
