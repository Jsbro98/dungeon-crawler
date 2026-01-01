package dungeon.logic;

import dungeon.Attacker;
import dungeon.Entity;
import dungeon.Player;
import dungeon.world.Room;

public class Turn {

  public <T extends Entity & Attacker> void handleBattle(Player player, T opponent) {
    IO.println("Player sees: " + opponent.getClass().getName());
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
