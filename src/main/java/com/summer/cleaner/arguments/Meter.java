package com.summer.cleaner.arguments;

/**
 * Метры. Всегда положительны.
 **/
public class Meter {

  private final int metersInt;

  private Meter(int metersInt) {
    this.metersInt = metersInt;
  }

  public static Meter of(int metersInt) {
    if (metersInt < 0) {
      throw new IllegalArgumentException("Число метров движения вперед должно быть положительным.");
    }
    return new Meter(metersInt);
  }

  public int getMetersInt() {
    return metersInt;
  }

  public Meter plus(Meter toAdd) {
    int toAddInt = toAdd.getMetersInt();
    return Meter.of(metersInt + toAddInt);
  }

  public Meter minus(Meter toRemove) {
    int toRemoveInt = toRemove.getMetersInt();
    return Meter.of(metersInt + toRemoveInt);
  }

  /**
   * Сравнивает больше ли текущий объект, чем сравниваемый.
   **/
  public boolean isMore(Meter metersToCheck) {
    int metersToCheckInt = metersToCheck.getMetersInt();
    return metersInt > metersToCheckInt;
  }
}