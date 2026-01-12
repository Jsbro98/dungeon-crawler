package dungeon.logic;

public class CommandDispatcher {

  public static ParsedCommand dispatchCommand(String input) {
    String[] inputSplit = input.split(" ", 2);
    String cmd = inputSplit[0];
    String arg = inputSplit.length > 1 ? inputSplit[1] : null;

    return switch (cmd) {
      case "exit" -> new ParsedCommand(Command.EXIT, null);
      case "directions" -> new ParsedCommand(Command.DIRECTIONS, null);
      case "attack" -> new ParsedCommand(Command.ATTACK, null);
      case "inventory" -> new ParsedCommand(Command.INVENTORY, null);
      case "go" -> new ParsedCommand(Command.MOVE, arg);
      case "pickup" -> new ParsedCommand(Command.PICKUP, arg);
      case "equip" -> new ParsedCommand(Command.EQUIP, arg);

      default -> throw new IllegalArgumentException("Unexpected input: " + input);
    };
  }

  public enum Command {
    MOVE, ATTACK, HEAL, DIRECTIONS, INVENTORY, PICKUP, EQUIP, EXIT
  }

  public record ParsedCommand(Command type, String argument) {
  }
}
