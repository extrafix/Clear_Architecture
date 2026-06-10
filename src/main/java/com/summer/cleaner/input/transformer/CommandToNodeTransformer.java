package com.summer.cleaner.input.transformer;

import com.summer.cleaner.ast.CommandNode;
import com.summer.cleaner.command.api.Command;
import java.util.ArrayList;
import java.util.List;

public class CommandToNodeTransformer {

  public List<CommandNode> exec(List<Command> commands) {
    List<CommandNode> commandNodes = new ArrayList<>();
    for (Command command : commands) {
      CommandNode commandNode = commandToNode(command);
      commandNodes.add(commandNode);
    }
    return commandNodes;
  }

  CommandNode commandToNode(
      Command command) {
    return new CommandNode(command);
  }
}
