package com.summer.cleaner.event;

import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.commons.lang3.tuple.Pair;

public class RobotMovedEvent
    extends DoneEvent {

  public RobotMovedEvent(UUID doneEventId,
                         UUID requestEventId,
                         Pair<CleanerImpl, OutMessage> cleanerAndOutMessage,
                         LocalDateTime eventDateTime) {
    super(doneEventId, requestEventId, cleanerAndOutMessage, eventDateTime);
  }

}