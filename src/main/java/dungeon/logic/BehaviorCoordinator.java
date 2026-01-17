package dungeon.logic;

import dungeon.world.Combatant;
import dungeon.ui.TextRenderer;
import dungeon.world.Room;

import java.util.Arrays;
import java.util.Scanner;

public class BehaviorCoordinator {

  public void handleBattle(Combatant player, Combatant opponent) {
    String opponentName = Arrays.stream(opponent.getClass().getName().split("\\.")).toList().getLast();
    IO.println("Player sees: " + opponentName);
    IO.println("Player moves in to attack");
    while (player.isAlive() && opponent.isAlive()) {
      player.attack(opponent);
      opponent.attack(player);

      IO.println(opponent);
      IO.println(player);
    }
  }

  public void handleBossBattle(Combatant player, Room bossRoom) {
    Scanner delayUntilReady = new Scanner(System.in);
    TextRenderer.describe(bossRoom);
    TextRenderer.printEnteringBossRoomMessage();
    IO.println("Prepare for the fight of your life! Press enter to start");
    delayUntilReady.nextLine();

    Combatant boss = (Combatant) bossRoom.getEntities().getFirst();
    handleBattle(player, boss);
  }

  public Room moveRoom(Room from, String direction) {
    Room to = from.getExit(direction);

    if (to != null) {
      System.out.println("Moving Rooms...");
      return to;
    }

    IO.println("this room does not have an exit at direction " + direction);
    return from;
  }
}
