package com.summer.cleaner.interpreter;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.input.transformer.InToCommandPostfixTransformer;
import com.summer.cleaner.input.transformer.InToCommandTransformer;
import com.summer.cleaner.input.transformer.ParsedStringToCommandTransformer;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import com.summer.cleaner.store.event.EventStore;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class CommandInterpreter {


  private final EventStore eventStore;


  InToCommandTransformer inToCommandTransformer = new InToCommandTransformer();

  ParsedStringToCommandTransformer parsedStringToCommandTransformer = new ParsedStringToCommandTransformer();

  InToCommandPostfixTransformer inToCommandPostfixTransformer = new InToCommandPostfixTransformer();

  public CommandInterpreter() {
    this.eventStore = new EventStore();
  }

  public CommandInterpreter(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  boolean execPostfix(String commandAndArgumentString) {
    List<String> commandStrings = inToCommandPostfixTransformer.execPostfix(commandAndArgumentString);
    return exec(commandStrings);
  }

  boolean exec(List<String> commandStrings) {
    List<Pair<String, Object>> parsedCommandsAndArguments = inToCommandTransformer.exec(
        commandStrings);
    List<Command> commands = parsedStringToCommandTransformer.exec(parsedCommandsAndArguments);
    Pair<CleanerImpl, List<OutMessage>> currentProgramResult = eventStore.getCurrentCleanerStateAndOutMessages();

    CleanerImpl currentCleaner = currentProgramResult.getLeft();
    List<OutMessage> outMessages = currentProgramResult.getRight();

    for (Command command : commands) {
      Pair<CleanerImpl, OutMessage> commandResult = command.exec(currentCleaner);
      currentCleaner = commandResult.getLeft();
      OutMessage nextOutMessage = commandResult.getRight();
      outMessages.add(nextOutMessage);
    }
    // Добавляем в store новые команды после их выполнения
    eventStore.addAll(commands);
    Pair<CleanerImpl, List<OutMessage>> programResult = Pair.of(currentCleaner, outMessages);
    outMessages.forEach(outMessage ->
        System.out.println(outMessage.text()));
    return true;
  }

}
