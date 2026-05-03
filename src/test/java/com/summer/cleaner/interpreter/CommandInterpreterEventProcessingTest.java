package com.summer.cleaner.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandInterpreterEventProcessingTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  private List<String> commands;

  private String expectedOutput;

  @BeforeEach
  public void setUp() {
    commands = Arrays.asList(
        "move 100",
        "move 10",
        "turn -90",
        "set soap",
        "start",
        "move 50",
        "stop"
    );
    expectedOutput =
        "POS 100,0" + System.lineSeparator() +
            "POS 110,0" + System.lineSeparator() +
            "ANGLE 270" + System.lineSeparator() +
            "STATE SOAP" + System.lineSeparator() +
            "START WITH SOAP" + System.lineSeparator() +
            "POS 110,50" + System.lineSeparator() +
            "STOP";
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void testExecSequentialCommands() {

    CommandInterpreter interpreter = CommandInterpreter.of();
    interpreter.exec(commands);

    assertEquals(expectedOutput, outputStreamCaptor.toString()
                                                   .trim());
  }

}