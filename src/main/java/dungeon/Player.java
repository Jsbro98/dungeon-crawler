package dungeon;

public class Player extends Entity {
  public Player(int health) {
    super(health);
  }

  @Override
  void heal(int healingValue) {
    setHealth(Math.max(100, healingValue + getHealth()));
  }

  @Override
  void takeDamage(int damageValue) {
    setHealth(Math.max(0, getHealth() - damageValue));
  }

  @Override
  public String toString() {
    return "Player stats: health-(" + getHealth() + ")";
  }
}
