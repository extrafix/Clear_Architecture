package com.summer.cleaner.function.impl;

import com.summer.cleaner.function.api.Start;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.Cleaner;
import com.summer.cleaner.robot.CleanerFunctionalStaticImpl;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

public class StartImpl implements Start {

  @Override
  public Pair<CleanerImpl, OutMessage> call(Cleaner cleaner, Object argument) {
    return CleanerFunctionalStaticImpl.start((CleanerImpl) cleaner);
  }
}
