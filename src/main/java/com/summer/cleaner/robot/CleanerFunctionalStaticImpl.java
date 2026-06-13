package com.summer.cleaner.robot;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.out.OutMessage;
import java.util.List;
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
    List<CleanMode> availableCleanModes = cleaner.availableCleanModes;
    Point nextPosition = currentField.move(
        currentPosition,
        metersToForward,
        angleRelationNorth);
    if (nextPosition == null) {
      String text = String.format(
          "Выполнение команды move %d м не возможно, т.к. робот выйдет за пределы поля",
          metersToForward.getMetersInt());
      OutMessage outMessage = new OutMessage(text);
      return Pair.of(cleaner, outMessage);
    }
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
        currentCleanMode,
        availableCleanModes
    );
    return Pair.of(updatedCleaner, outMessage);
  }


  public static Pair<CleanerImpl, OutMessage> turn(CleanerImpl cleaner, Angle angle) {
    Field currentField = cleaner.currentField;
    Point currentPosition = cleaner.currentPosition;
    CleanMode currentCleanMode = cleaner.currentCleanMode;
    List<CleanMode> availableCleanModes = cleaner.availableCleanModes;

    String text = String.format(
        "ANGLE %d",
        angle.getAngleInt());
    OutMessage outMessage = new OutMessage(text);
    CleanerImpl updatedCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        angle,
        currentCleanMode,
        availableCleanModes);
    return Pair.of(updatedCleaner, outMessage);
  }


  public static Pair<CleanerImpl, OutMessage> set(CleanerImpl cleaner, CleanMode cleanMode) {
    boolean isUnavailable = !cleaner.availableCleanModes.contains(cleanMode);
    if (isUnavailable) {
      String text = String.format(
          "Среди доступных для выбора состояний [ %s ] нет STATE %s",
          String.join(", ",
              cleaner.availableCleanModes.stream().map(mode -> mode.name().toUpperCase()).toList()),
          cleanMode.name().toUpperCase());
      OutMessage outMessage = new OutMessage(text);
      return Pair.of(cleaner, outMessage);
    }

    Field currentField = cleaner.currentField;
    Point currentPosition = cleaner.currentPosition;
    Angle angleRelationNorth = cleaner.angleRelationNorth;
    List<CleanMode> availableCleanModes = cleaner.availableCleanModes;
    String text = String.format(
        "STATE %s",
        cleanMode.name().toUpperCase());
    OutMessage outMessage = new OutMessage(text);
    CleanerImpl updatedCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        angleRelationNorth,
        cleanMode,
        availableCleanModes);
    assert updatedCleaner.currentCleanMode == cleanMode;
    return Pair.of(updatedCleaner, outMessage);
  }

  public static Pair<CleanerImpl, OutMessage> start(CleanerImpl cleaner) {
    String text = String.format(
        "START WITH %s",
        cleaner.currentCleanMode.name().toUpperCase());
    OutMessage outMessage = new OutMessage(text);
    return Pair.of(cleaner, outMessage);
  }

  public static Pair<CleanerImpl, OutMessage> stop(CleanerImpl cleaner) {
    String text = String.format("STOP");
    OutMessage outMessage = new OutMessage(text);
    return Pair.of(cleaner, outMessage);
  }

  public static Pair<CleanerImpl, OutMessage> stop_2(Cleaner cleaner, Object argument) {
    return stop((CleanerImpl) cleaner);
  }
}
