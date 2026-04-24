package com.summer.cleaner.di;

import java.util.Map;

public class DependencyContainerImpl
    implements DependencyContainer {

  private final Map<String, Object> dependencies;

  public DependencyContainerImpl(Map<String, Object> dependencies) {
    this.dependencies = dependencies;
  }

  @Override
  public Object get(String dependencyKey) {
    return dependencies.get(dependencyKey);
  }

  @Override
  public boolean put(
      String dependencyKey,
      Object dependency) {
    dependencies.put(dependencyKey, dependency);
    return true;
  }
}
