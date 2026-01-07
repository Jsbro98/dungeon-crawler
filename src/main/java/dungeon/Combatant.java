package dungeon;

public abstract class Combatant extends Entity implements Attacker {
  protected Combatant(int health) {
    super(health);
  }
}
