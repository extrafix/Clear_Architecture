package com.summer.cleaner.event;

import com.summer.cleaner.event.api.Event;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Родитель с конструктором основных полей для DoneEvent.
 */
public class DoneEvent implements Event {

  final UUID doneEventId;

  final UUID requestEventId;

  final Pair<CleanerImpl, OutMessage> cleanerAndOutMessage;

  final LocalDateTime eventDateTime;

  /**
   * Родитель с конструктором основных полей для DoneEvent.
   */
  public DoneEvent(UUID doneEventId,
                   UUID requestEventId,
                   Pair<CleanerImpl, OutMessage> cleanerAndOutMessage,
                   LocalDateTime eventDateTime) {
    this.doneEventId = doneEventId;
    this.requestEventId = requestEventId;
    this.cleanerAndOutMessage = cleanerAndOutMessage;
    this.eventDateTime = eventDateTime;
  }

}
