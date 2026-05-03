package com.summer.cleaner.function.api;

import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.Cleaner;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

@FunctionalInterface
public interface Move{

  Pair<CleanerImpl, OutMessage> call(Cleaner cleaner, Meter argument);
}
