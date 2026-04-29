package com.summer.cleaner.interpreter;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.function.api.UniversalFunction;
import com.summer.cleaner.function.impl.UniversalFunctionImpl;
import com.summer.cleaner.input.transformer.InToCommandPostfixTransformer;
import com.summer.cleaner.input.transformer.InToCommandTransformer;
import com.summer.cleaner.input.transformer.ParsedStringToCommandTransformer;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import java.util.ArrayList;
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

  ParsedStringToCommandTransformer parsedStringToCommandTransformer = new ParsedStringToCommandTransformer();

  InToCommandPostfixTransformer inToCommandPostfixTransformer = new InToCommandPostfixTransformer();

  public CommandInterpreter() {
    this.universalFunction = new UniversalFunctionImpl();
  }

  public CommandInterpreter(UniversalFunction universalFunction) {
    this.universalFunction = new UniversalFunctionImpl();
  }

  boolean execPostfix(String commandAndArgumentString) {
    List<String> commandStrings = inToCommandPostfixTransformer.execPostfix(commandAndArgumentString);
    return exec(commandStrings);
  }

  boolean exec(List<String> commandStrings) {
    List<Pair<String, Object>> parsedCommandsAndArguments = inToCommandTransformer.exec(
        commandStrings);
    List<Command> commands = parsedStringToCommandTransformer.exec(parsedCommandsAndArguments);

    CleanerImpl currentCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        Angle.of(0),
        CleanMode.WATER);

    List<OutMessage> outMessages = new ArrayList<>();
    for (Command command : commands) {
      Pair<CleanerImpl, OutMessage> commandResult = command.exec(currentCleaner);
      currentCleaner = commandResult.getLeft();
      OutMessage nextOutMessage = commandResult.getRight();
      outMessages.add(nextOutMessage);
    }
    Pair<CleanerImpl, List<OutMessage>> programResult = Pair.of(currentCleaner, outMessages);
    outMessages.forEach(outMessage ->
        System.out.println(outMessage.text()));
    return true;
  }

}
