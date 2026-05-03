package com.summer.cleaner.processor.impl;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.event.DoneEvent;
import com.summer.cleaner.event.RequestEvent;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.processor.api.EventProcessor;
import com.summer.cleaner.robot.CleanerImpl;
import com.summer.cleaner.store.event.EventStore;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.tuple.Pair;

public abstract class EventProcessorAbstract<R extends RequestEvent, D extends DoneEvent>
    implements EventProcessor {

  private final EventStore eventStore;

  public EventProcessorAbstract(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  /**
   * По исполнению команды генерируется исходящее событие.
   *
   * @param requestEvent входящее событие
   * @return потомки DoneEvent
   */
  @Override
  public D exec(RequestEvent requestEvent) {
    Pair<CleanerImpl, List<OutMessage>> currentProgramResult = eventStore.getCurrentCleanerStateAndOutMessages();

    CleanerImpl currentCleaner = currentProgramResult.getLeft();
    List<OutMessage> outMessages = currentProgramResult.getRight();

    Command command = requestEvent.command;
    Pair<CleanerImpl, OutMessage> commandResult = command.exec(currentCleaner);

    // Добавляем в store новое событие после их выполнения
    UUID requestEventId = requestEvent.requestEventId;
    DoneEvent doneEvent = new DoneEvent(
        UUID.randomUUID(),
        requestEventId,
        commandResult,
        LocalDateTime.now()
    );
    eventStore.add(doneEvent);
    return (D) doneEvent;
  }

}
