package dungeon;

public abstract class Entity {
  private int health;

  abstract void heal(int healingValue);
  abstract void takeDamage(int damageValue);

  public int getHealth() {
    return this.health;
  }
}
