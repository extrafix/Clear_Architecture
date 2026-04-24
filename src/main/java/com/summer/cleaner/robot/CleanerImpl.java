package com.summer.cleaner.robot;


import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;


public class CleanerImpl
    implements Cleaner {

  public final Point currentPosition;

  public final Field currentField;

  public final Angle angleRelationNorth;

  public final CleanMode currentCleanMode;

  private CleanerImpl(
      Point currentPosition,
      Field currentField,
      Angle angleRelationNorth,
      CleanMode currentCleanMode) {
    this.currentPosition = currentPosition;
    this.currentField = currentField;
    this.angleRelationNorth = angleRelationNorth;
    this.currentCleanMode = currentCleanMode;
  }

  public static CleanerImpl of(
      Point currentPosition,
      Field currentField,
      Angle angleRelationNorth,
      CleanMode currentCleanMode) {
    boolean isOutBorder = !currentField.isInBorder(currentPosition);
    if (isOutBorder) {
      throw new IllegalArgumentException("Начальная точка должна находиться в границах поля.");
    }
    return new CleanerImpl(
        currentPosition,
        currentField,
        angleRelationNorth,
        currentCleanMode);
  }


}