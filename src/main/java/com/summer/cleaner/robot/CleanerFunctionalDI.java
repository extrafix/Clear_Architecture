package com.summer.cleaner.robot;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.function.api.Move;
import com.summer.cleaner.function.api.Set;
import com.summer.cleaner.function.api.Start;
import com.summer.cleaner.function.api.Stop;
import com.summer.cleaner.function.api.Turn;
import com.summer.cleaner.out.OutMessage;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Функции для работы с CleanerImpl.
 */
public class CleanerFunctionalDI {

  private final Move move;

  private final Turn turn;

  private final Set set;

  private final Stop stop;

  private final Start start;

  public CleanerFunctionalDI(
      Move move,
      Turn turn,
      Set set,
      Start start,
      Stop stop) {
    this.move = move;
    this.turn = turn;
    this.set = set;
    this.start = start;
    this.stop = stop;
  }

  public Pair<CleanerImpl, OutMessage> move(Cleaner cleaner, Meter argument) {
    return move.call(cleaner, argument);
  }

  public Pair<CleanerImpl, OutMessage> turn(Cleaner cleaner, Angle argument) {
    return turn.call(cleaner, argument);
  }

  public Pair<CleanerImpl, OutMessage> set(Cleaner cleaner, CleanMode argument) {
    return set.call(cleaner, argument);
  }

  public Pair<CleanerImpl, OutMessage> start(Cleaner cleaner, Object argument) {
    return start.call(cleaner, argument);
  }

  public Pair<CleanerImpl, OutMessage> stop(Cleaner cleaner, Object argument) {
    return stop.call(cleaner, argument);
  }
}
