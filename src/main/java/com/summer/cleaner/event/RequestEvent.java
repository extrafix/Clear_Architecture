package com.summer.cleaner.event;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.event.api.Event;
import java.time.LocalDateTime;
import java.util.UUID;

public class RequestEvent
    implements Event {

  public final UUID requestEventId;

  public final Command command;

  public final LocalDateTime eventDateTime;

  /**
   * Родитель с конструктором основных полей для RequestEvent.
   */
  public RequestEvent(UUID requestEventId,
                      Command command,
                      LocalDateTime eventDateTime) {
    this.requestEventId = requestEventId;
    this.command = command;
    this.eventDateTime = eventDateTime;
  }


}
