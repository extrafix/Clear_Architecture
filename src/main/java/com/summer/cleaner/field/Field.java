package com.summer.cleaner.field;

import com.summer.cleaner.arguments.Angle;
import com.summer.cleaner.arguments.Meter;
import com.summer.cleaner.arguments.Point;

public class Field {

  private final Meter[][] positions;

  private final Meter x;

  private final Meter y;

  /**
   * В конструктуре задается размер поля x на y.
   **/
  public Field(Meter x, Meter y) {
    this.x = x;
    this.y = y;
    this.positions = new Meter[x.getMetersInt()][y.getMetersInt()];
  }

  /**
   * Выполняет нормализацию, что бы новые значения координат были внутри поля.
   **/
  public Point normalizeCoordinates(Meter nextX, Meter nextY) {
    if (nextX.isMore(x)) {
      nextX = x;
    }
    if (nextY.isMore(y)) {
      nextY = y;
    }
    return new Point(nextX, nextY);
  }

  /**
   * Проверка, что точка находится в границах поля.
   **/
  public boolean isInBorder(Point point) {
    boolean isXinBorder = x.isMore(point.x());
    boolean isYinBorder = y.isMore(point.y());
    return isXinBorder && isYinBorder;
  }


  public Point move(
      Point currentPosition,
      Meter metersToForward,
      Angle angleRelationNorth) {
    Meter nextX = currentPosition.x();
    Meter nextY = currentPosition.y();
    int currentAngleInt = angleRelationNorth.getAngleInt();
    if (currentAngleInt == 0) {
      nextX = currentPosition.x().plus(metersToForward); // Вправо
    }
    if (currentAngleInt == 90) {
      nextY = currentPosition.y().plus(metersToForward); // Вниз
    }
    if (currentAngleInt == 180) {
      nextX = currentPosition.x().minus(metersToForward); // Влево
    }
    if (currentAngleInt == 270) {
      nextY = currentPosition.y().minus(metersToForward);// Вверх
    }
    return normalizeCoordinates(nextX, nextY);
  }
}