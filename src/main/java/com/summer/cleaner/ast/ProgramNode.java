package com.summer.cleaner.ast;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Узел для программы.
 **/
public class ProgramNode {

  private final List<CommandNode> commandNodes;

  private final CleanerImpl initialState;

  /**
   * Узел для программы.
   **/
  public ProgramNode(
      List<CommandNode> commandNodes,
      CleanerImpl initialState) {
    this.commandNodes = commandNodes;
    this.initialState = initialState;
  }

  /**
   * Узел для программы.
   **/
  public ProgramNode(
      List<CommandNode> commandNodes) {
    this.commandNodes = commandNodes;
    Point currentPosition = new Point(
        Meter.of(0),
        Meter.of(0));

    Field currentField = new Field(
        Meter.of(500),
        Meter.of(500));

    this.initialState = CleanerImpl.of(
        currentPosition,
        currentField,
        Angle.of(0),
        CleanMode.WATER);
  }

  public Pair<CleanerImpl, List<OutMessage>> execute() {
    List<OutMessage> outMessages = new ArrayList<>();
    Pair<CleanerImpl, OutMessage> currentState = null;
    CleanerImpl currentCleaner = initialState;
    for (CommandNode commandNode : commandNodes) {
      currentState = commandNode.execute(currentCleaner);
      currentCleaner = currentState.getLeft();
      outMessages.add(currentState.getRight());
    }
    return Pair.of(currentCleaner, outMessages);
  }
}
