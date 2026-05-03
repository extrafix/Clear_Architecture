package com.summer.cleaner.interpreter;

import com.summer.cleaner.command.api.Command;
import com.summer.cleaner.event.DoneEvent;
import com.summer.cleaner.event.MoveRequestedEvent;
import com.summer.cleaner.event.RequestEvent;
import com.summer.cleaner.input.transformer.CommandToRequestEventTransformer;
import com.summer.cleaner.input.transformer.InToCommandPostfixTransformer;
import com.summer.cleaner.input.transformer.InToCommandTransformer;
import com.summer.cleaner.input.transformer.ParsedStringToCommandTransformer;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.processor.api.EventProcessor;
import com.summer.cleaner.processor.impl.CommonEventProcessor;
import com.summer.cleaner.processor.impl.MoveEventProcessor;
import com.summer.cleaner.robot.CleanerImpl;
import com.summer.cleaner.store.event.CommandStore;
import com.summer.cleaner.store.event.EventStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

public class CommandInterpreter {

  private final List<EventProcessor<? extends RequestEvent, ? extends DoneEvent>> processors;

  private final Map<Class<? extends RequestEvent>, EventProcessor<? extends RequestEvent, ? extends DoneEvent>> processorsByEventMap;

  public void subscribe(EventProcessor<? extends RequestEvent, ? extends DoneEvent> listener) {
    processors.add(listener);
  }

  private final CommandStore commandStore;

  private final EventStore eventStore;

  InToCommandTransformer inToCommandTransformer = new InToCommandTransformer();

  ParsedStringToCommandTransformer parsedStringToCommandTransformer = new ParsedStringToCommandTransformer();

  InToCommandPostfixTransformer inToCommandPostfixTransformer = new InToCommandPostfixTransformer();

  CommandToRequestEventTransformer commandToRequestEventTransformer = new CommandToRequestEventTransformer();

  public CommandInterpreter() {
    this.processors = new ArrayList<>();
    this.processorsByEventMap = new HashMap<>();
    this.eventStore = new EventStore();
    this.commandStore = new CommandStore();
  }

  public CommandInterpreter(Map<Class<? extends RequestEvent>, EventProcessor<? extends RequestEvent, ? extends DoneEvent>> processorsByEventMap,
                            List<EventProcessor<? extends RequestEvent, ? extends DoneEvent>> processors,
                            EventStore eventStore,
                            CommandStore commandStore) {
    this.processorsByEventMap = processorsByEventMap;
    this.processors = processors;
    this.eventStore = eventStore;
    this.commandStore = commandStore;
  }

  public static CommandInterpreter of() {
    List<EventProcessor<? extends RequestEvent, ? extends DoneEvent>> processors = new ArrayList<>();
    final EventStore eventStore = new EventStore();
    final Map<Class<? extends RequestEvent>, EventProcessor<? extends RequestEvent, ? extends DoneEvent>> processorsByEventMap = new HashMap<>();
    CommonEventProcessor<? extends RequestEvent, ? extends DoneEvent> commonEventProcessor = new CommonEventProcessor(
        eventStore);

    MoveEventProcessor moveEventProcessor = new MoveEventProcessor(eventStore);
    processorsByEventMap.put(MoveRequestedEvent.class, moveEventProcessor);
    processorsByEventMap.put(RequestEvent.class, commonEventProcessor);
    processors.add(moveEventProcessor);
    return new CommandInterpreter(processorsByEventMap,
                                  processors,
                                  eventStore,
                                  new CommandStore()
    );
  }

  boolean execPostfix(String commandAndArgumentString) {
    List<String> commandStrings = inToCommandPostfixTransformer.execPostfix(
        commandAndArgumentString);
    return exec(commandStrings);
  }

  boolean exec(List<String> commandStrings) {
    List<Pair<String, Object>> parsedCommandsAndArguments = inToCommandTransformer.exec(
        commandStrings);
    List<Command> commands = parsedStringToCommandTransformer.exec(parsedCommandsAndArguments);
    // сгенерировать по List<Command> MoveRequestedEvent
    List<RequestEvent> requestEvents = commandToRequestEventTransformer.exec(commands);
    for (RequestEvent requestEvent : requestEvents) {
      // добавить сохранение в State входящего события
      eventStore.add(requestEvent);
      Class clazz = requestEvent.getClass();

      EventProcessor processor = processorsByEventMap.get(clazz);
      if (processor == null) {
        processor = processorsByEventMap.get(RequestEvent.class);
      }
      processor.exec(requestEvent);
    }

    Pair<CleanerImpl, List<OutMessage>> currentProgramResult = eventStore.getCurrentCleanerStateAndOutMessages();

    CleanerImpl currentCleaner = currentProgramResult.getLeft();
    List<OutMessage> outMessages = currentProgramResult.getRight();

    Pair<CleanerImpl, List<OutMessage>> programResult = Pair.of(currentCleaner, outMessages);
    outMessages.forEach(outMessage ->
                            System.out.println(outMessage.text()));
    return true;
  }

}
