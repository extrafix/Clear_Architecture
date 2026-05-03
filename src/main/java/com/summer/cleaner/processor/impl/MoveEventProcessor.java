package com.summer.cleaner.processor.impl;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.event.MoveRequestedEvent;
import com.summer.cleaner.event.RobotMovedEvent;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.processor.api.EventProcessor;
import com.summer.cleaner.robot.CleanerImpl;
import com.summer.cleaner.store.event.EventStore;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.tuple.Pair;

public class MoveEventProcessor
    implements EventProcessor<MoveRequestedEvent, RobotMovedEvent> {

  private final EventStore eventStore;

  public MoveEventProcessor(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  @Override
  public RobotMovedEvent exec(MoveRequestedEvent moveRequestEvent) {
    Pair<CleanerImpl, List<OutMessage>> currentProgramResult = eventStore.getCurrentCleanerStateAndOutMessages();
    CleanerImpl currentCleaner = currentProgramResult.getLeft();
    List<OutMessage> outMessages = currentProgramResult.getRight();
    Command command = moveRequestEvent.command;
    Pair<CleanerImpl, OutMessage> commandResult = command.exec(currentCleaner);

    // Добавляем в store новое событие после их выполнения
    UUID requestEventId = moveRequestEvent.requestEventId;
    RobotMovedEvent robotMovedEvent = new RobotMovedEvent(
        UUID.randomUUID(),
        requestEventId,
        commandResult,
        LocalDateTime.now()
    );
    eventStore.add(robotMovedEvent);
    return robotMovedEvent;
  }


}
