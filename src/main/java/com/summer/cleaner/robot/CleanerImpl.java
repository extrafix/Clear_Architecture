package com.summer.cleaner.robot;


import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.out.OutMessage;


public class CleanerImpl
    implements Cleaner {

  private Point currentPosition;

  private final Field currentField;

  private Angle angleRelationNorth;

  private CleanMode currentCleanMode;

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

  public static Cleaner of(
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

  @Override
  public OutMessage move(Meter metersToForward) {

    Point nextPosition = currentField.move(
        currentPosition,
        metersToForward,
        angleRelationNorth);
    currentPosition = nextPosition;
    String text = String.format(
        "POS %d,%d",
        nextPosition.x().getMetersInt(),
        nextPosition.y().getMetersInt());
    return new OutMessage(text);
  }

  @Override
  public OutMessage turn(Angle angle) {
    this.angleRelationNorth = angle;

    String text = String.format(
        "ANGLE %d",
        angle.getAngleInt());
    return new OutMessage(text);
  }

  @Override
  public OutMessage set(CleanMode cleanMode) {
    this.currentCleanMode = cleanMode;

    String text = String.format(
        "STATE %s",
        currentCleanMode.name().toUpperCase());
    return new OutMessage(text);
  }

  @Override
  public OutMessage start() {
    String text = String.format(
        "START WITH %s",
        currentCleanMode.name().toUpperCase());
    return new OutMessage(text);
  }

  @Override
  public OutMessage stop() {
    String text = String.format("STOP");
    return new OutMessage(text);
  }

}