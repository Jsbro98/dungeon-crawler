package dungeon;

public interface Attacker {
  int getDamage();
  int getDamageVariance();
  void attack(Entity other);
}
