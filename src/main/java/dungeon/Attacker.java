package dungeon;

public interface Attacker {
  int getDamage();
  void attack(Entity other);
}
