package com.summer.cleaner.function.impl;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.function.api.UniversalFunction;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerFunctionalDI;
import com.summer.cleaner.robot.CleanerFunctionalStaticImpl;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

public class UniversalFunctionImpl
    implements UniversalFunction {

  private final CleanerFunctionalDI cleanerFunctional;

  public UniversalFunctionImpl() {
    cleanerFunctional = new CleanerFunctionalDI(
        new MoveImpl(),
        new TurnImpl(),
        (cleaner, argument) -> CleanerFunctionalStaticImpl.set((CleanerImpl) cleaner, argument),
        new StartImpl(),
        CleanerFunctionalStaticImpl::stop_2);
  }

  @Override
  public Pair<CleanerImpl, OutMessage> callCommand(
      CleanerImpl cleaner,
      String commandKey,
      Object argument) {

    switch (commandKey) {
      case "move":
        return cleanerFunctional.move(cleaner, (Meter) argument);

      case "turn":
        return cleanerFunctional.turn(cleaner, (Angle) argument);

      case "set":
        return cleanerFunctional.set(cleaner, (CleanMode) argument);

      case "start":
        return cleanerFunctional.start(cleaner, argument);

      case "stop":
        return cleanerFunctional.stop(cleaner, argument);

      default:
        throw new IllegalArgumentException("Unknown command key: " + commandKey);
    }
  }

}
