package com.summer.cleaner.event;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.robot.CleanerImpl;
import java.time.LocalDateTime;
import java.util.UUID;

public class MoveRequestedEvent extends RequestEvent{

  public MoveRequestedEvent(UUID requestEventId,
                            Command command,
                            LocalDateTime eventDateTime) {
    super(requestEventId, command, eventDateTime);
  }

}
