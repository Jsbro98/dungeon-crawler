package dungeon;

public class Ogre extends Entity {
  private final int DAMAGE = 75;

  public Ogre(int health) {
    super(health);
  }

  public void attack(Entity other) {
    other.takeDamage(DAMAGE);
  }

  @Override
  public String toString() {
    return "Ogre: health(" + getHealth() + ")";
  }
}
