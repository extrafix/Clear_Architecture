package com.summer.cleaner.processor.impl;

import com.summer.cleaner.event.DoneEvent;
import com.summer.cleaner.event.RequestEvent;
import com.summer.cleaner.store.event.EventStore;

public class CommonEventProcessor<R extends RequestEvent, D extends DoneEvent>
    extends EventProcessorAbstract {

  public CommonEventProcessor(EventStore eventStore) {
    super(eventStore);
  }

}
