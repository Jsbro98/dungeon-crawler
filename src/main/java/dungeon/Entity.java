package dungeon;

public abstract class Entity {
  private int health;
  private final int maxHealth;

  protected Entity(int health) {
    this.health = health;
    this.maxHealth = health;
  }

  public void heal(int healingValue) {
    health = Math.max(maxHealth, healingValue + health);
  }

  public void takeDamage(int damageValue) {
    setHealth(Math.max(0, getHealth() - damageValue));
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}
