package com.summer.cleaner.input.transformer;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.command.impl.CommandMoveImpl;
import com.summer.cleaner.command.impl.CommandSetImpl;
import com.summer.cleaner.command.impl.CommandStartImpl;
import com.summer.cleaner.command.impl.CommandStopImpl;
import com.summer.cleaner.command.impl.CommandTurnImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class ParsedStringToCommandTransformer {

  public List<Command> exec(List<Pair<String, Object>> parsedCommandsAndArguments) {

    List<Command> commands = new ArrayList<>();
    for (Pair<String, Object> parsedCommandAndArgument : parsedCommandsAndArguments) {
      Command command = parsedCommandStringAndArgumentToCommandObject(parsedCommandAndArgument);
      commands.add(command);
    }
    return commands;
  }

  Command parsedCommandStringAndArgumentToCommandObject(
      Pair<String, Object> parsedCommandAndArgument) {

    String typeOfCommand = parsedCommandAndArgument.getLeft();

    switch (typeOfCommand) {
      case "move":
        return new CommandMoveImpl(parsedCommandAndArgument);

      case "turn":
        return new CommandTurnImpl(parsedCommandAndArgument);

      case "set":
        return new CommandSetImpl(parsedCommandAndArgument);

      case "start":
        return new CommandStartImpl(parsedCommandAndArgument);

      case "stop":
        return new CommandStopImpl(parsedCommandAndArgument);

      default:
        throw new IllegalArgumentException("Unknown command: " + typeOfCommand);
    }
  }
}
