package com.summer.cleaner.command.impl;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerFunctionalStaticImpl;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

public class CommandStopImpl
    implements Command {

  private final String commandName;

  private final Object argument;

  public CommandStopImpl(Pair<String, Object> parsedCommandAndArgument) {
    this.commandName = parsedCommandAndArgument.getLeft();
    this.argument = parsedCommandAndArgument.getRight();
  }

  @Override
  public Pair<CleanerImpl, OutMessage> exec(CleanerImpl cleaner) {
    return CleanerFunctionalStaticImpl.stop(cleaner);
  }
}
