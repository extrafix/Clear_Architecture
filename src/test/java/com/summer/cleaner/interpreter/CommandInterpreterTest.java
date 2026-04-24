package com.summer.cleaner.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.summer.cleaner.configuration.CleanerOopConfiguration;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandInterpreterTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void testExecSequentialCommands() {
    List<String> commands = Arrays.asList(
        "move 100",
        "turn -90",
        "set soap",
        "start",
        "move 50",
        "stop"
    );
    CleanerOopConfiguration.init();
    CommandInterpreter interpreter = CleanerOopConfiguration.CommandInterpreter();
    interpreter.exec(commands);

    String expectedOutput =
        "POS 100,0" + System.lineSeparator() +
            "ANGLE 270" + System.lineSeparator() +
            "STATE SOAP" + System.lineSeparator() +
            "START WITH SOAP" + System.lineSeparator() +
            "POS 100,50" + System.lineSeparator() +
            "STOP";

    assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
  }
}