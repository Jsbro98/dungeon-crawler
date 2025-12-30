package dungeon;

public class Player extends Entity {
  private final Inventory inventory;

  public Player(int health) {
    super(health);
    this.inventory = new Inventory(10);
  }

  @Override
  public String toString() {
    return "Player stats: health-(" + getHealth() + ")";
  }
}
