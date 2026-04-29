package com.summer.cleaner.store.event;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class EventStore {

  private final CleanerImpl initialState;

  private final List<Command> commandEventList;

  public EventStore(CleanerImpl initialState, List<Command> commandEventList) {
    this.initialState = initialState;
    this.commandEventList = commandEventList;
  }

  public EventStore(CleanerImpl initialState) {
    this.initialState = initialState;
    this.commandEventList = new ArrayList<>();
  }

  public EventStore() {
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
    this.commandEventList = new ArrayList<>();
  }

  /**
   * Высчитывает текущее состояние на основе начального и списка выполненных команд.
   * @return текущее состояние CleanerImpl.
   */
  public CleanerImpl getCurrentCleanerState() {
    CleanerImpl currentCleaner = initialState;
    for (Command command : commandEventList) {
      Pair<CleanerImpl, OutMessage> commandResult = command.exec(currentCleaner);
      currentCleaner = commandResult.getLeft();
    }
    return currentCleaner;
  }

  /**
   * Высчитывает текущее состояние на основе начального и списка выполненных команд.
   * @return текущее состояние CleanerImpl и список OutMessages.
   */
  public Pair<CleanerImpl, List<OutMessage>>  getCurrentCleanerStateAndOutMessages() {
    CleanerImpl currentCleaner = initialState;
    List<OutMessage> outMessages = new ArrayList<>();
    for (Command command : commandEventList) {
      Pair<CleanerImpl, OutMessage> commandResult = command.exec(currentCleaner);
      currentCleaner = commandResult.getLeft();
      OutMessage nextOutMessage = commandResult.getRight();
      outMessages.add(nextOutMessage);
    }
    return Pair.of(currentCleaner, outMessages);
  }

  public boolean addAll(List<Command> commands) {
    commandEventList.addAll(commands);
    return true;
  }
}
