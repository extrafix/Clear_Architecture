package com.summer.cleaner.configuration;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.di.DependencyContainer;
import com.summer.cleaner.di.DependencyContainerImpl;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.interpreter.CommandInterpreter;
import com.summer.cleaner.interpreter.CommandInterpreterImpl;
import com.summer.cleaner.interpreter.InToCommandTransformer;
import com.summer.cleaner.interpreter.InToCommandTransformerImpl;
import com.summer.cleaner.robot.Cleaner;
import com.summer.cleaner.robot.CleanerImpl;
import java.util.HashMap;
import java.util.Map;

public class CleanerOopConfiguration {

  private static Map<String, Object> dependensiesMap = new HashMap<>();

  private static DependencyContainer dependencyContainer = new DependencyContainerImpl(
      dependensiesMap);

  ;


  public static boolean init() {

    dependencyContainer.put("Cleaner", Cleaner(dependencyContainer));
    dependencyContainer.put("CommandInterpreter", CommandInterpreter(dependencyContainer));
    dependencyContainer.put("InToCommandTransformer", InToCommandTransformer(dependencyContainer));
    dependencyContainer.put("CommandInterpreter", CommandInterpreter(dependencyContainer));
    return true;
  }

  public static CommandInterpreter CommandInterpreter(DependencyContainer dependencyContainer) {
    return new CommandInterpreterImpl(
        Cleaner(dependencyContainer),
        InToCommandTransformer(dependencyContainer)
    );
  }
  public static CommandInterpreter CommandInterpreter() {
    CommandInterpreter commandInterpreter = (CommandInterpreter) dependencyContainer.get(
        "CommandInterpreter");
    return commandInterpreter;
  }

  public static InToCommandTransformer InToCommandTransformer(DependencyContainer dependencyContainer) {
    InToCommandTransformer inToCommandTransformer = (InToCommandTransformer) dependencyContainer.get(
        "InToCommandTransformer");
    if (inToCommandTransformer != null) {
      return inToCommandTransformer;
    }
    return new InToCommandTransformerImpl();
  }

  public static InToCommandTransformer InToCommandTransformer() {
    InToCommandTransformer inToCommandTransformer = (InToCommandTransformer) dependencyContainer.get(
        "InToCommandTransformer");
    return inToCommandTransformer;
  }


  public static Cleaner Cleaner(DependencyContainer dependencyContainer) {
    Cleaner cleaner = (Cleaner) dependencyContainer.get("Cleaner");
    if (cleaner != null) {
      return cleaner;
    }
    Point currentPosition = new Point(
        Meter.of(0),
        Meter.of(0));

    Field currentField = new Field(
        Meter.of(500),
        Meter.of(500));
    return CleanerImpl.of(
        currentPosition,
        currentField,
        Angle.of(0),
        CleanMode.WATER);
  }
}

