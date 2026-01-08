package dungeon.logic;

public class CommandDispatcher {

  public static ParsedCommand dispatchCommand(String input) {
    return switch (input) {
      case "exit" -> new ParsedCommand(Command.EXIT, null);
      case "directions" -> new ParsedCommand(Command.DIRECTIONS, null);
      case "attack" -> new ParsedCommand(Command.ATTACK, null);
      case "heal" -> new ParsedCommand(Command.HEAL, null);
      case "inventory" -> new ParsedCommand(Command.INVENTORY, null);

      default -> {
        if (input.startsWith("go ")) {
          String direction = input.substring(3);
          yield new ParsedCommand(Command.MOVE, direction);
        } else {
          throw new IllegalArgumentException("unexpected input: " + input);
        }
      }
    };
  }

  public enum Command {
    MOVE,
    ATTACK,
    HEAL,
    DIRECTIONS,
    INVENTORY,
    EXIT
  }

  public record ParsedCommand(Command type, String argument) {
  }
}
