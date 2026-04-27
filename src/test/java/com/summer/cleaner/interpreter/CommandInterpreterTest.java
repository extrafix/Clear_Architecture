package com.summer.cleaner.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.function.impl.MoveImpl;
import com.summer.cleaner.function.impl.StartImpl;
import com.summer.cleaner.function.impl.TurnImpl;
import com.summer.cleaner.robot.CleanerFunctionalDI;
import com.summer.cleaner.robot.CleanerFunctionalStaticImpl;
import com.summer.cleaner.robot.CleanerImpl;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandInterpreterTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  private List<String> commands;

  private String expectedOutput;

  @BeforeEach
  public void setUp() {
    commands = Arrays.asList(
        "move 100",
        "turn -90",
        "set soap",
        "start",
        "move 50",
        "stop"
    );
    expectedOutput =
        "POS 100,0" + System.lineSeparator() +
            "ANGLE 270" + System.lineSeparator() +
            "STATE SOAP" + System.lineSeparator() +
            "START WITH SOAP" + System.lineSeparator() +
            "POS 100,50" + System.lineSeparator() +
            "STOP";
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void testExecSequentialCommands() {

    CommandInterpreter interpreter = new CommandInterpreter();
    interpreter.exec(commands);

    assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
  }


  @Test
  void testExecUniversalCommand() {

    final CleanerFunctionalDI cleanerFunctional = new CleanerFunctionalDI(
        new MoveImpl(),
        new TurnImpl(),
        (cleaner, argument) -> CleanerFunctionalStaticImpl.set((CleanerImpl) cleaner, argument),
        new StartImpl(),
        CleanerFunctionalStaticImpl::stop_2);

    CommandInterpreter interpreter = new CommandInterpreter((
        cleaner,
        commandKey,
        argument) -> {
      switch (commandKey) {
        case "move":
          return cleanerFunctional.move(cleaner, (Meter) argument);

        case "turn":
          return cleanerFunctional.turn(cleaner, (Angle) argument);

        case "set":
          return cleanerFunctional.set(cleaner, (CleanMode) argument);

        case "start":
          return cleanerFunctional.start(cleaner, argument);

        case "stop":
          return cleanerFunctional.stop(cleaner, argument);

        default:
          throw new IllegalArgumentException("Unknown command key: " + commandKey);
      }
    });

    interpreter.exec(commands);

    assertEquals(expectedOutput, outputStreamCaptor.toString().

        trim());
  }
}