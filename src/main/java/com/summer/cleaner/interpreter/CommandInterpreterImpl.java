package com.summer.cleaner.interpreter;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.Cleaner;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class CommandInterpreterImpl
    implements CommandInterpreter {

  private final InToCommandTransformer inToCommandTransformer;

  private final Cleaner cleaner;

  public CommandInterpreterImpl(Cleaner cleaner, InToCommandTransformer inToCommandTransformer) {
    this.cleaner = cleaner;
    this.inToCommandTransformer = inToCommandTransformer;
  }

  @Override
  public boolean exec(List<String> commandStrings) {
    List<Pair<String, Object>> parsedCommandsAndArguments = inToCommandTransformer.exec(
        commandStrings);

    // цикл по вхождениям EntryMap
    parsedCommandsAndArguments.forEach(pair -> {
      OutMessage outMessage = callCommand(pair);
      System.out.println(outMessage.text());
    });
    return true;
  }

  private OutMessage callCommand(
      Pair<String, Object> pair) {
    String commandKey = pair.getKey();
    Object argument = pair.getValue();
    return callCommand(commandKey, argument);
  }

  private OutMessage callCommand(
      String commandKey,
      Object argument) {
    switch (commandKey) {
      case "move":
        return cleaner.move((Meter) argument);

      case "turn":
        return cleaner.turn((Angle) argument);

      case "set":
        return cleaner.set((CleanMode) argument);

      case "start":
        return cleaner.start();

      case "stop":
        return cleaner.stop();

      default:
        throw new IllegalArgumentException("Unknown command key: " + commandKey);
    }
  }
}
