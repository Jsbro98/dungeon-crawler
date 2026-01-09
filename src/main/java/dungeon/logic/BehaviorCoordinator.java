package dungeon.logic;

import dungeon.Combatant;
import dungeon.world.Room;

import java.util.Arrays;

public class BehaviorCoordinator {

  public void handleBattle(Combatant player, Combatant opponent) {
    String opponentName = Arrays.stream(opponent.getClass().getName().split("\\.")).toList().getLast();
    IO.println("Player sees: " + opponentName);
    IO.println("Player moves in to attack");
    while (player.isAlive() && opponent.isAlive()) {
      player.attack(opponent);
      opponent.attack(player);

      IO.println(opponent);
      IO.println(player);
    }
  }

  public Room moveRoom(Room from, String direction) {
    Room to = from.getExit(direction);

    if (to != null) {
      System.out.println("Moving Rooms...");
      return to;
    }

    return from;
  }
}
