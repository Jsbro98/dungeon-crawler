package dungeon;

public class Player extends Entity {
  public Player(int health) {
    super(health);
  }

  @Override
  public String toString() {
    return "Player stats: health-(" + getHealth() + ")";
  }
}
