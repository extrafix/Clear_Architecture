package com.summer.cleaner.function.api;

import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

@FunctionalInterface
public interface UniversalFunction {

  Pair<CleanerImpl, OutMessage> callCommand(
      CleanerImpl cleaner,
      String commandKey,
      Object argument);

}
