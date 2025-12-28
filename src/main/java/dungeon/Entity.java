package dungeon;

public abstract class Entity {
  private int health;
  private final int maxHealth;

  protected Entity(int health) {
    this.health = health;
    this.maxHealth = health;
  }

  public void heal(int healingValue) {
    setHealth(Math.min(maxHealth, healingValue + health));
  }

  public void takeDamage(int damageValue) {
    setHealth(Math.max(0, health - damageValue));
  }

  public boolean isDead() {
    return health == 0;
  }

  public boolean isAlive() {
    return health > 0;
  }

  // getters & setters

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}
