package com.summer.cleaner.function.impl;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.function.api.Turn;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.Cleaner;
import com.summer.cleaner.robot.CleanerFunctionalStaticImpl;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

public class TurnImpl implements Turn {

  @Override
  public Pair<CleanerImpl, OutMessage> call(Cleaner cleaner, Angle argument) {
    return CleanerFunctionalStaticImpl.turn((CleanerImpl) cleaner, argument);
  }
}
