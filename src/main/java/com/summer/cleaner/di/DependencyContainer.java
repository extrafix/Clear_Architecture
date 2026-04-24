package com.summer.cleaner.di;

/**
 * Контейнер зависимостей.
 */
public interface DependencyContainer {

  Object get(String dependencyKey);

  boolean put(
      String dependencyKey,
      Object dependency);

}
