package com.summer.cleaner.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.summer.cleaner.input.transformer.InToCommandPostfixTransformer;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class InToCommandPostfixTransformerTest {

  @Test
  void execPostfix() {
    String inputCommands = "100 move -90 turn soap set start 50 move stop";
    List<String> expectedCommands = Arrays.asList(
        "move 100",
        "turn -90",
        "set soap",
        "start",
        "move 50",
        "stop"
    );
    InToCommandPostfixTransformer inToCommandPostfixTransformer = new InToCommandPostfixTransformer();
    List<String> actual = inToCommandPostfixTransformer.execPostfix(inputCommands);
    assertEquals(expectedCommands, actual);
  }
}