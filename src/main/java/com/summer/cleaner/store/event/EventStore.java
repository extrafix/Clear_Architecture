package com.summer.cleaner.store.event;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.event.RequestEvent;
import com.summer.cleaner.event.api.Event;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class EventStore {

  private final CleanerImpl initialState;

  private final List<Event> eventList;

  public EventStore(CleanerImpl initialState,
                    List<Event> eventList) {
    this.initialState = initialState;
    this.eventList = eventList;
  }

  public EventStore(CleanerImpl initialState) {
    this.initialState = initialState;
    this.eventList = new ArrayList<>();
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
    this.eventList = new ArrayList<>();
  }

  /**
   * Высчитывает текущее состояние на основе начального и списка выполненных команд.
   *
   * @return текущее состояние CleanerImpl и список OutMessages.
   */
  public Pair<CleanerImpl, List<OutMessage>> getCurrentCleanerStateAndOutMessages() {
    CleanerImpl currentCleaner = initialState;
    List<OutMessage> outMessages = new ArrayList<>();
    List<Command> commands = eventList.stream()
                                      .filter(event -> event instanceof RequestEvent)
                                      .map(event -> (RequestEvent) event)
                                      .map(
                                          (RequestEvent requestEvent) -> {
                                            return requestEvent.command;
                                          }
                                      )
                                      .toList();
    for (Command command : commands) {
      Pair<CleanerImpl, OutMessage> commandResult = command.exec(currentCleaner);
      currentCleaner = commandResult.getLeft();
      OutMessage nextOutMessage = commandResult.getRight();
      outMessages.add(nextOutMessage);
    }
    return Pair.of(currentCleaner, outMessages);
  }

  public boolean add(Event event) {
    eventList.add(event);
    return true;
  }

}
