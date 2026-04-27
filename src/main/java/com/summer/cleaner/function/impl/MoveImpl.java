package com.summer.cleaner.function.impl;

import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.function.api.Move;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.Cleaner;
import com.summer.cleaner.robot.CleanerFunctionalStaticImpl;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

public class MoveImpl
    implements Move {

  @Override
  public Pair<CleanerImpl, OutMessage> call(Cleaner cleaner, Meter argument) {
    return CleanerFunctionalStaticImpl.move((CleanerImpl) cleaner, argument);
  }
}
