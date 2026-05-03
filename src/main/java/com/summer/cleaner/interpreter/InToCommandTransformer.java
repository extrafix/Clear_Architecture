package com.summer.cleaner.interpreter;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class InToCommandTransformer {

  List<Pair<String, Object>> exec(List<String> commandStrings) {
    List<Pair<String, Object>> parsedCommandsAndArguments = new ArrayList<>();
    for (String command : commandStrings) {
      String[] commandAndArgument = command.split(" ");
      boolean isWithArgument = commandAndArgument.length > 1;
      if (isWithArgument) {
        parseCommandWithArg(commandAndArgument, parsedCommandsAndArguments);
      } else {
        Pair<String, Object> pair = Pair.of(command, null);
        parsedCommandsAndArguments.add(pair);
      }
    }
    return parsedCommandsAndArguments;
  }

  void parseCommandWithArg(
      String[] commandAndArgument,
      List<Pair<String, Object>> parsedCommandsAndArguments) {

    String typeOfCommand = commandAndArgument[0];
    String argumentString = commandAndArgument[1];
    Object valueCasted = null;

    switch (typeOfCommand) {
      case "move":
        int meterInt = Integer.parseInt(argumentString);
        valueCasted = Meter.of(meterInt);
        break;

      case "turn":
        int angleInt = Integer.parseInt(argumentString);
        valueCasted = Angle.of(angleInt);
        break;

      case "set":
        String modeString = argumentString.toUpperCase();
        valueCasted = CleanMode.valueOf(modeString);
        break;

      default:
        throw new IllegalArgumentException("Unknown command: " + typeOfCommand);
    }
    Pair<String, Object> pair = Pair.of(typeOfCommand, valueCasted);
    parsedCommandsAndArguments.add(pair);
  }
}
