package com.summer.cleaner.arguments;


public class Angle{

  private final int angleInt;

  private Angle(int angleInt){
    this.angleInt = angleInt;
  }

  public int getAngleInt(){
    return angleInt;
  }

  public static Angle of(int angleInt){
    boolean isOutBorder = Math.abs(angleInt) > 360;
    if(isOutBorder){
      throw new IllegalArgumentException("Угол по модулю должен быть в границах 360.");
    }
    int positiveAngleInt = getPositiveEquivalent(angleInt);
    return new Angle(positiveAngleInt);
  }

  /**
   * Преобразует значение угла в его положительный аналогв пределах -360.
   **/
  public static int  getPositiveEquivalent(int angleInt) {
    if (angleInt < 0) {
      angleInt = angleInt + 360;
    }
    return angleInt;
  }

}
