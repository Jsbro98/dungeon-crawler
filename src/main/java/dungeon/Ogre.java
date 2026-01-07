package dungeon;

import java.util.Random;

public class Ogre extends Combatant {
  private static final Random OGRE_RANDOM = new Random();
  private static final int MAX_VARIANCE = 5;
  private final int strength;

  public Ogre(int health, int strength) {
    super(health);
    this.strength = strength;
  }

  @Override
  public String toString() {
    return "Ogre: health(" + getHealth() + ")";
  }

  @Override
  public int getDamage() {
    return strength + (OGRE_RANDOM.nextInt(getDamageVariance()));
  }

  @Override
  public int getDamageVariance() {
    return MAX_VARIANCE;
  }
}
