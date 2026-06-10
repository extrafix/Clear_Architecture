package com.summer.cleaner.ast;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Узел для команды.
 **/
public class CommandNode {

  private final Command command;

  /**
   * Узел для команды.
   **/
  public CommandNode(Command command) {
    this.command = command;
  }

  public Pair<CleanerImpl, OutMessage> execute(CleanerImpl state) {
    return command.exec(state);
  }
}
