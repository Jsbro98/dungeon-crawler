package dungeon;

public interface Attacker {
  int getDamage();

  int getDamageVariance();

  default void attack(Entity other) {
    other.takeDamage(this.getDamage());
  }
}
