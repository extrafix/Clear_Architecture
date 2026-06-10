package com.summer.cleaner.interpreter;

import com.summer.cleaner.ast.CommandNode;
import com.summer.cleaner.ast.ProgramNode;
import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.input.transformer.CommandToNodeTransformer;
import com.summer.cleaner.input.transformer.InToCommandTransformer;
import com.summer.cleaner.input.transformer.ParsedStringToCommandTransformer;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Интерпретатор AST.
 **/
public class AstInterpreter {

  InToCommandTransformer inToCommandTransformer = new InToCommandTransformer();

  ParsedStringToCommandTransformer parsedStringToCommandTransformer = new ParsedStringToCommandTransformer();

  CommandToNodeTransformer commandToNodeTransformer = new CommandToNodeTransformer();

  boolean exec(List<String> commandStrings) {
    List<Pair<String, Object>> parsedCommandsAndArguments = inToCommandTransformer.exec(
        commandStrings);
    List<Command> commands = parsedStringToCommandTransformer.exec(parsedCommandsAndArguments);
    List<CommandNode> commandNodes = commandToNodeTransformer.exec(commands);
    ProgramNode programNode = new ProgramNode(commandNodes);

    Pair<CleanerImpl, List<OutMessage>> currentProgramResult = programNode.execute();

    List<OutMessage> outMessages = currentProgramResult.getRight();
    outMessages.forEach(outMessage ->
        System.out.println(outMessage.text()));
    return true;
  }
}
