package dungeon.logic;

import dungeon.Attacker;
import dungeon.Entity;
import dungeon.world.Room;

public class Turn {

  public <T extends Entity & Attacker> void handleBattle(T one, T two) {
    while (one.isAlive() && two.isAlive()) {
      one.attack(two);
      two.attack(one);

      IO.println(one);
      IO.println(two);
    }
  }

  public Room moveRoom(Room from, String direction) {
    Room to = from.getExit(direction);

    if (to != null) {
      return to;
    }

    return from;
  }
}
