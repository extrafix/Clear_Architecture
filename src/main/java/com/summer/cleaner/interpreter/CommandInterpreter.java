package com.summer.cleaner.interpreter;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.function.api.UniversalFunction;
import com.summer.cleaner.function.impl.UniversalFunctionImpl;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class CommandInterpreter {


  private final UniversalFunction universalFunction;

  Point currentPosition = new Point(
      Meter.of(0),
      Meter.of(0));

  Field currentField = new Field(
      Meter.of(500),
      Meter.of(500));

  InToCommandTransformer inToCommandTransformer = new InToCommandTransformer();

  public CommandInterpreter() {
    this.universalFunction = new UniversalFunctionImpl();
  }

  public CommandInterpreter(UniversalFunction universalFunction) {
    this.universalFunction = new UniversalFunctionImpl();
  }


  boolean exec(List<String> commandStrings) {
    List<Pair<String, Object>> parsedCommandsAndArguments = inToCommandTransformer.exec(
        commandStrings);

    CleanerImpl currentCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        Angle.of(0),
        CleanMode.WATER);
    for (Pair<String, Object> pair : parsedCommandsAndArguments) {
      Pair<CleanerImpl, OutMessage> updatedCleanerAndOutMessage = callCommand(currentCleaner, pair);
      currentCleaner = updatedCleanerAndOutMessage.getLeft();
      OutMessage outMessage = updatedCleanerAndOutMessage.getRight();
      System.out.println(outMessage.text());
    }

    return true;
  }

  private Pair<CleanerImpl, OutMessage> callCommand(
      CleanerImpl cleaner,
      Pair<String, Object> pair) {
    String commandKey = pair.getKey();
    Object argument = pair.getValue();
    return universalFunction.callCommand(cleaner, commandKey, argument);
  }


}
