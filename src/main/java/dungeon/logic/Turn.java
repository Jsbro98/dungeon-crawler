package dungeon.logic;

import dungeon.Attacker;
import dungeon.Entity;

public class Turn {
  public <T extends Entity & Attacker> void handleBattle(T one, T two) {
    while (one.isAlive() && two.isAlive()) {
      one.attack(two);
      two.attack(one);
    }
  }
}
