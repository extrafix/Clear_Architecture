package com.summer.cleaner.command;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerFunctionalStaticImpl;
import com.summer.cleaner.robot.CleanerImpl;
import org.apache.commons.lang3.tuple.Pair;

public class RobotCommandImpl
    implements RobotCommand {

  private final OutMessage outMessage;

  private final CleanerImpl cleaner;

  public RobotCommandImpl(OutMessage outMessage, CleanerImpl cleaner) {
    this.outMessage = outMessage;
    this.cleaner = cleaner;
  }

  @Override
  public RobotCommand move(Meter argument) {
    Pair<CleanerImpl, OutMessage> cleanerAndOutMessage = CleanerFunctionalStaticImpl.move(
        cleaner,
        argument);
    return out(cleanerAndOutMessage);
  }

  @Override
  public RobotCommand turn(Angle argument) {
    Pair<CleanerImpl, OutMessage> cleanerAndOutMessage = CleanerFunctionalStaticImpl.turn(
        cleaner,
        argument);
    return out(cleanerAndOutMessage);
  }

  @Override
  public RobotCommand set(CleanMode cleanMode) {
    Pair<CleanerImpl, OutMessage> cleanerAndOutMessage = CleanerFunctionalStaticImpl.set(
        cleaner,
        cleanMode);
    return out(cleanerAndOutMessage);
  }

  @Override
  public RobotCommand start() {
    Pair<CleanerImpl, OutMessage> cleanerAndOutMessage = CleanerFunctionalStaticImpl.start(
        cleaner);
    return out(cleanerAndOutMessage);
  }

  @Override
  public RobotCommand stop() {
    Pair<CleanerImpl, OutMessage> cleanerAndOutMessage = CleanerFunctionalStaticImpl.stop(
        cleaner);
    return out(cleanerAndOutMessage);
  }

  @Override
  public OutMessage getOutMessage() {
    return outMessage;
  }

  @Override
  public CleanerImpl getCleaner() {
    return cleaner;
  }

  private RobotCommand out(Pair<CleanerImpl, OutMessage> cleanerAndOutMessage) {
    OutMessage outMessage = cleanerAndOutMessage.getRight();
    System.out.println(outMessage.text());
    return new RobotCommandImpl(outMessage, cleanerAndOutMessage.getLeft());
  }
}
