package dungeon;

public abstract class Entity {
  private int health;

  protected Entity(int health) {
    this.health = health;
  }

  abstract void heal(int healingValue);

  abstract void takeDamage(int damageValue);

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }
}
