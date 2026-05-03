package com.summer.cleaner.function.impl;

import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.function.api.Set;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.Cleaner;
import com.summer.cleaner.robot.CleanerFunctionalStaticImpl;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

public class SetImpl implements Set {

  @Override
  public Pair<CleanerImpl, OutMessage> call(Cleaner cleaner, CleanMode argument) {
    return CleanerFunctionalStaticImpl.set((CleanerImpl) cleaner, argument);
  }
}
