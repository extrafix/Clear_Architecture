package com.summer.cleaner.command.api;

import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

public interface Command {


  Pair<CleanerImpl, OutMessage> exec(CleanerImpl cleaner);
}