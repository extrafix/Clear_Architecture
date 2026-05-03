package com.summer.cleaner.robot;

import com.summer.cleaner.function.Stop;
import com.summer.cleaner.out.OutMessage;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Функции для работы с CleanerImpl.
 */
public class CleanerFunctionalImpl {


  private final Stop stop;

  public CleanerFunctionalImpl(Stop stop) {
    this.stop = stop;
  }

  Pair<CleanerImpl, OutMessage> stop(Cleaner cleaner, Object argument) {
    return stop.call(cleaner, argument);
  }
}
