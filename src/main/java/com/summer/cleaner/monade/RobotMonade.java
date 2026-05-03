package com.summer.cleaner.monade;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.out.OutMessage;
import com.summer.cleaner.robot.CleanerImpl;

public interface RobotMonade {

  RobotMonade move(Meter argument);

  RobotMonade turn(Angle angle);

  RobotMonade set(CleanMode cleanMode);

  RobotMonade start();

  RobotMonade stop();

  OutMessage getOutMessage();

  CleanerImpl getCleaner();

}
