package com.summer.cleaner.robot;


import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class CleanerImpl
    implements Cleaner {

  public final Point currentPosition;

  public final Field currentField;

  public final Angle angleRelationNorth;

  public final CleanMode currentCleanMode;

  public final List<CleanMode> availableCleanModes;

  private CleanerImpl(
      Point currentPosition,
      Field currentField,
      Angle angleRelationNorth,
      CleanMode currentCleanMode,
      List<CleanMode> availableCleanModes) {
    this.currentPosition = currentPosition;
    this.currentField = currentField;
    this.angleRelationNorth = angleRelationNorth;
    this.currentCleanMode = currentCleanMode;
    this.availableCleanModes = new ArrayList<>(availableCleanModes);
  }

  private CleanerImpl(
      Point currentPosition,
      Field currentField,
      Angle angleRelationNorth,
      CleanMode currentCleanMode) {
    this.currentPosition = currentPosition;
    this.currentField = currentField;
    this.angleRelationNorth = angleRelationNorth;
    this.currentCleanMode = currentCleanMode;
    this.availableCleanModes = Arrays.asList(CleanMode.values());
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

  public static CleanerImpl of(
      Point currentPosition,
      Field currentField,
      Angle angleRelationNorth,
      CleanMode currentCleanMode,
      List<CleanMode> availableCleanModes) {
    boolean isOutBorder = !currentField.isInBorder(currentPosition);
    if (isOutBorder) {
      throw new IllegalArgumentException("Начальная точка должна находиться в границах поля.");
    }
    return new CleanerImpl(
        currentPosition,
        currentField,
        angleRelationNorth,
        currentCleanMode,
        availableCleanModes);
  }


}
