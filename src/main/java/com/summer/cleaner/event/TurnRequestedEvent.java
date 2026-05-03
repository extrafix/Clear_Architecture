package com.summer.cleaner.event;

import com.summer.cleaner.command.api.Command;
import java.time.LocalDateTime;
import java.util.UUID;

public class TurnRequestedEvent
    extends RequestEvent{

  public TurnRequestedEvent(UUID requestEventId,
                            Command command,
                            LocalDateTime eventDateTime) {
    super(requestEventId, command, eventDateTime);
  }

}
