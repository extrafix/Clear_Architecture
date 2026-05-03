package com.summer.cleaner.robot;


import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import java.util.UUID;


public class CleanerImpl
    implements Cleaner {

  public final UUID robotId;

  public final Point currentPosition;

  public final Field currentField;

  public final Angle angleRelationNorth;

  public final CleanMode currentCleanMode;

  private CleanerImpl(
      UUID robotId,
      Point currentPosition,
      Field currentField,
      Angle angleRelationNorth,
      CleanMode currentCleanMode) {
    this.robotId = robotId;
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
    checkIsOutBorder(currentPosition, currentField);
    UUID robotId = UUID.randomUUID();
    return new CleanerImpl(
        robotId,
        currentPosition,
        currentField,
        angleRelationNorth,
        currentCleanMode);
  }

  public static CleanerImpl of(
      UUID robotId,
      Point currentPosition,
      Field currentField,
      Angle angleRelationNorth,
      CleanMode currentCleanMode) {
    checkIsOutBorder(currentPosition, currentField);
    return new CleanerImpl(
        robotId,
        currentPosition,
        currentField,
        angleRelationNorth,
        currentCleanMode);
  }

  private static void checkIsOutBorder(Point currentPosition,
                                       Field currentField) {
    boolean isOutBorder = !currentField.isInBorder(currentPosition);
    if (isOutBorder) {
      throw new IllegalArgumentException("Начальная точка должна находиться в границах поля.");
    }
  }


}