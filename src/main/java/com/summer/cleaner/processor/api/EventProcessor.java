package com.summer.cleaner.processor.api;

import com.summer.cleaner.event.DoneEvent;
import com.summer.cleaner.event.RequestEvent;

public interface EventProcessor<R extends RequestEvent, D extends DoneEvent> {
  /**
   * По исполнению команды генерируется исходящее событие.
   * @param requestEvent входящее событие
   * @return потомки DoneEvent
   */
  D exec(R requestEvent);
}
