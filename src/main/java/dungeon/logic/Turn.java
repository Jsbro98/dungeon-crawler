package dungeon.logic;

import dungeon.Attacker;
import dungeon.Entity;
import dungeon.Player;
import dungeon.world.Room;

import java.util.Arrays;

public class Turn {

  /*TODO: find a way to reimplement this
      function where opponent doesn't need to be casted*/
  public void handleBattle(Player player, Entity opponent) {
    String opponentName = Arrays.stream(opponent.getClass().getName().split("\\.")).toList().getLast();
    IO.println("Player sees: " + opponentName);
    IO.println("Player moves in to attack");
    // adding a cast here, will fix
    Attacker target = (Attacker) opponent;
    while (player.isAlive() && opponent.isAlive()) {
      player.attack(opponent);
      target.attack(player);

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
