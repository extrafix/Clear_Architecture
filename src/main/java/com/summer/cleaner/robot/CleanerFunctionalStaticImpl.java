package com.summer.cleaner.robot;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.CleanMode;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;
import com.summer.cleaner.field.Field;
import com.summer.cleaner.out.OutMessage;
import org.apache.commons.lang3.tuple.Pair;
import  com.summer.cleaner.dto.*;

/**
 * Функции для работы с CleanerImpl.
 */
public class CleanerFunctionalStaticImpl {


  public static Pair<CleanerImpl, OutMessage> move(CleanerImpl cleaner, Meter metersToForward) {
    Field currentField = cleaner.currentField;
    Point currentPosition = cleaner.currentPosition;
    Angle angleRelationNorth = cleaner.angleRelationNorth;
    CleanMode currentCleanMode = cleaner.currentCleanMode;
    Pair<Boolean,Point> isNormalizedAndNextPosition = currentField.moveWithCheckNormalize(
        currentPosition,
        metersToForward,
        angleRelationNorth);
    Point nextPosition = isNormalizedAndNextPosition.getRight();
    String text = String.format(
        "POS %d,%d",
        nextPosition.x().getMetersInt(),
        nextPosition.y().getMetersInt());
      boolean isNormalized = isNormalizedAndNextPosition.getLeft();
     ValidationMessage validationMessage = checkMoveStatus();
    OutMessage outMessage = new OutMessage(text, validationMessage);
    CleanerImpl updatedCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        angleRelationNorth,
        currentCleanMode);
    return Pair.of(updatedCleaner, outMessage);
  }

  ValidationMessage checkMoveStatus(boolean isNormalized){
	  if(isNormalized){
	  	MoveResponse.HIT_BARRIER;
	  	}
	  return MoveResponse.MOVE_OK;
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
    ValidationMessage validationMessage = checkResources(cleanMode);
    OutMessage outMessage = new OutMessage(text, validationMessage);
      CleanerImpl updatedCleaner = CleanerImpl.of(
        currentPosition,
        currentField,
        angleRelationNorth,
        cleanMode);
    return Pair.of(updatedCleaner, outMessage);
  }

  
public SetStateResponse checkResources(CleanMode newMode) {
    if (newMode == CleanMode.WATER) {
        return SetStateResponse.NO_WATER;
    } else if (newMode == CleanMode.SOAP) {
        return SetStateResponse.NO_SOAP;
    }
    
    return SetStateResponse.OK;
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
