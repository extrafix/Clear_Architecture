package com.summer.cleaner.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.robot.CleanerImpl;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RobotCommandImplTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  private String expectedOutput;

  @BeforeEach
  public void setUp() {
    expectedOutput =
        "POS 100,0" + System.lineSeparator() +
            "ANGLE 270" + System.lineSeparator() +
            "STATE SOAP" + System.lineSeparator() +
            "START WITH SOAP" + System.lineSeparator() +
            "POS 100,50" + System.lineSeparator() +
            "STOP";
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @DisplayName("Тест программы конкретной уборки")
  @Test
  void testRoomOneProgram() {
    Point currentPosition = new Point(
        Meter.of(0),
        Meter.of(0));

    Field currentField = new Field(
        Meter.of(500),
        Meter.of(500));

    CleanerImpl currentCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        Angle.of(0),
        CleanMode.WATER);

    Arrays.asList(
        "move 100",
        "turn -90",
        "set soap",
        "start",
        "move 50",
        "stop"
    );

    RobotCommand robotCommandImpl = new RobotCommandImpl(null, currentCleaner);

    robotCommandImpl.move(Meter.of(100))
        .turn(Angle.of(-90))
        .set(CleanMode.SOAP)
        .start()
        .move(Meter.of(50))
        .stop();
    assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
  }
}