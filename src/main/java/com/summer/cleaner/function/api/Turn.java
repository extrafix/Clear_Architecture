package com.summer.cleaner.function.api;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.Cleaner;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

@FunctionalInterface
public interface Turn {

  Pair<CleanerImpl, OutMessage> call(Cleaner cleaner, Angle argument);
}