package com.summer.cleaner.input.transformer;

import java.util.ArrayList;
import java.util.List;

public class InToCommandPostfixTransformer {

  public List<String> execPostfix(String commandAndArgumentString) {
    String[] inputParts = commandAndArgumentString.split(" ");

    String currentArgument = null;
    List<String> commandAndArguments = new ArrayList<>();
    for (String inputPart : inputParts) {
      if (isArgument(inputPart)) {
        currentArgument = inputPart;
      } else {
        if (isNeedArgumentCommand(inputPart)) {
          assert currentArgument != null
              : "На этапе парсинга команды требующей аргументы, аргумент уже должен быть распаршен";
          String oneCommand = inputPart + " " + currentArgument;
          commandAndArguments.add(oneCommand);
        } else {
          String oneCommand = inputPart;
          commandAndArguments.add(oneCommand);
        }
        currentArgument = null;
      }
    }
    return commandAndArguments;
  }

  private final static List<String> TYPE_OF_COMMAND = List.of(
      "move",
      "turn",
      "set",
      "start",
      "stop");

  private final static List<String> COMMANDS_NEED_ARGUMENT = List.of("move", "turn", "set");

  boolean isArgument(String parsedPart) {
    return TYPE_OF_COMMAND.stream().noneMatch(command -> command.equalsIgnoreCase(parsedPart));
  }

  boolean isNeedArgumentCommand(String parsedPart) {
    return COMMANDS_NEED_ARGUMENT.stream()
        .anyMatch(command -> command.equalsIgnoreCase(parsedPart));
  }
}
