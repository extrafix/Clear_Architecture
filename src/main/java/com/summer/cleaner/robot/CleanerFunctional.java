package com.summer.cleaner.robot;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.out.OutMessage;

public interface CleanerFunctional {

  OutMessage move(Meter metersToForward);

  OutMessage turn(Angle angle);

  OutMessage set(CleanMode cleanMode);

  OutMessage start();

  OutMessage stop();

}
