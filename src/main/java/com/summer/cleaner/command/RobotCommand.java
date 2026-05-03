package com.summer.cleaner.command;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;

public interface RobotCommand {

  RobotCommand move(Meter argument);

  RobotCommand turn(Angle angle);

  RobotCommand set(CleanMode cleanMode);

  RobotCommand start();

  RobotCommand stop();

  OutMessage getOutMessage();

  CleanerImpl getCleaner();

}
