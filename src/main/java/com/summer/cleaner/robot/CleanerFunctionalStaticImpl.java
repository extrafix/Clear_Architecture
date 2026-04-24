package com.summer.cleaner.robot;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.out.OutMessage;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Функции для работы с CleanerImpl.
 */
public class CleanerFunctionalStaticImpl {


  public static Pair<CleanerImpl, OutMessage> move(CleanerImpl cleaner, Meter metersToForward) {
    Field currentField = cleaner.currentField;
    Point currentPosition = cleaner.currentPosition;
    Angle angleRelationNorth = cleaner.angleRelationNorth;
    CleanMode currentCleanMode = cleaner.currentCleanMode;
    Point nextPosition = currentField.move(
        currentPosition,
        metersToForward,
        angleRelationNorth);
    currentPosition = nextPosition;
    String text = String.format(
        "POS %d,%d",
        nextPosition.x().getMetersInt(),
        nextPosition.y().getMetersInt());
    OutMessage outMessage = new OutMessage(text);
    CleanerImpl updatedCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        angleRelationNorth,
        currentCleanMode);
    return Pair.of(updatedCleaner, outMessage);
  }


  public static Pair<CleanerImpl, OutMessage> turn(CleanerImpl cleaner, Angle angle) {
    Field currentField = cleaner.currentField;
    Point currentPosition = cleaner.currentPosition;
    CleanMode currentCleanMode = cleaner.currentCleanMode;

    String text = String.format(
        "ANGLE %d",
        angle.getAngleInt());
    OutMessage outMessage = new OutMessage(text);
    CleanerImpl updatedCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        angle,
        currentCleanMode);
    return Pair.of(updatedCleaner, outMessage);
  }


  public static Pair<CleanerImpl, OutMessage> set(CleanerImpl cleaner, CleanMode cleanMode) {
    Field currentField = cleaner.currentField;
    Point currentPosition = cleaner.currentPosition;
    Angle angleRelationNorth = cleaner.angleRelationNorth;
    String text = String.format(
        "STATE %s",
        cleanMode.name().toUpperCase());
    OutMessage outMessage = new OutMessage(text);
    CleanerImpl updatedCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        angleRelationNorth,
        cleanMode);
    assert updatedCleaner.currentCleanMode == cleanMode;
    return Pair.of(updatedCleaner, outMessage);
  }

  public static Pair<CleanerImpl, OutMessage>  start(CleanerImpl cleaner) {
    String text = String.format(
        "START WITH %s",
        cleaner.currentCleanMode.name().toUpperCase());
    OutMessage outMessage = new OutMessage(text);
    return Pair.of(cleaner, outMessage);
  }

  public static Pair<CleanerImpl, OutMessage>  stop(CleanerImpl cleaner) {
    String text = String.format("STOP");
    OutMessage outMessage = new OutMessage(text);
    return Pair.of(cleaner, outMessage);
  }

  public static Pair<CleanerImpl, OutMessage>  stop_2(Cleaner cleaner, Object argument) {
    return stop((CleanerImpl) cleaner);
  }
}
