package com.summer.cleaner.interpreter;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Преобразовать входного списка строк в список команд и аргументов.
 */
public interface InToCommandTransformer {

  List<Pair<String, Object>> exec(List<String> commandStrings);
}
