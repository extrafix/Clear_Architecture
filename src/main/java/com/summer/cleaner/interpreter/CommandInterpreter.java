package com.summer.cleaner.interpreter;

import java.util.List;

/**
 * Интерпретатор команд клиента.
 */
public interface CommandInterpreter {

  boolean exec(List<String> commandStrings);
}
