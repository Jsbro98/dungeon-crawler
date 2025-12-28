package dungeon;

public class Player extends Entity {
  private int health;

  public Player() {
    this.health = 100;
  }


  public void setHealth(int health) {
    this.health = health;
  }

  @Override
  void heal(int healingValue) {
    this.health = Math.max(100, healingValue + this.health);
  }

  @Override
  void takeDamage(int damageValue) {
    this.health = Math.max(0, this.health - damageValue);
  }

  @Override
  public String toString() {
    return "Player stats: health-(" + this.health + ")";
  }
}
