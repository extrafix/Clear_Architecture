package com.summer.cleaner.input.transformer;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.command.impl.CommandMoveImpl;
import com.summer.cleaner.command.impl.CommandSetImpl;
import com.summer.cleaner.command.impl.CommandStartImpl;
import com.summer.cleaner.command.impl.CommandStopImpl;
import com.summer.cleaner.command.impl.CommandTurnImpl;
import com.summer.cleaner.event.MoveRequestedEvent;
import com.summer.cleaner.event.RequestEvent;
import com.summer.cleaner.event.SetRequestedEvent;
import com.summer.cleaner.event.StartRequestedEvent;
import com.summer.cleaner.event.StopRequestedEvent;
import com.summer.cleaner.event.TurnRequestedEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandToRequestEventTransformer {

  public List<RequestEvent> exec(List<Command> commands) {
    List<RequestEvent> requestEvents = new ArrayList<>();
    for (Command command : commands) {
      RequestEvent requestEvent = generateRequestEvent(command);
      requestEvents.add(requestEvent);
    }
    return requestEvents;
  }

  private RequestEvent generateRequestEvent(Command command){

    UUID requestEventId = UUID.randomUUID();
    LocalDateTime localDateTimeNow = LocalDateTime.now();
    if(command instanceof CommandMoveImpl){
      return new MoveRequestedEvent(requestEventId,
                                    command,
                                    localDateTimeNow);
    }
    if(command instanceof CommandTurnImpl){
      return new TurnRequestedEvent(requestEventId,
                                    command,
                                    localDateTimeNow);
    }
    if(command instanceof CommandSetImpl){
      return new SetRequestedEvent(requestEventId,
                                   command,
                                   localDateTimeNow);
    }
    if(command instanceof CommandStartImpl){
      return new StartRequestedEvent(requestEventId,
                                     command,
                                     localDateTimeNow);
    }
    if(command instanceof CommandStopImpl){
      return new StopRequestedEvent(requestEventId,
                                    command,
                                    localDateTimeNow);
    }else{
      throw new IllegalArgumentException("Неизвестная команда: " + command);
    }
  }

}
