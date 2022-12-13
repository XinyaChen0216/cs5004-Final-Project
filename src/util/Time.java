package util;

/**
 * This represents a Time class, which consists of two field. They are
 * startTime,
 * and endTime.
 * 
 * @author xinyachen
 *
 */
public class Time {

  private int startTime;
  private int endTime;

  /**
   * A constructor that takes in start time and end time as arguments to create
   * Time object.
   * 
   * @param startTime the start time of action
   * @param endTime   the end time of action
   * @throws IllegalArgumentException when the start time and/or end time is
   *                                  negative, or
   *                                  when end time is less than start time
   */
  public Time(int startTime, int endTime) throws IllegalArgumentException {
    if (startTime < 0 || endTime < 0) {
      throw new IllegalArgumentException("Time should be non-negative.");
    }
    if (startTime > endTime) {
      throw new IllegalArgumentException("End time cannot be less than start time.");
    }
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Get the start time.
   * 
   * @return the start time
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Get the end time.
   * 
   * @return the end time
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Set end time.
   */
  public void setEndTime(int time) {
    this.endTime = time;
  }

  /**
   * Return a String description of Time.
   */
  @Override
  public String toString() {
    String s = "";
    s += "Appears at t=" + this.startTime + "\n";
    s += "Disappears at t=" + this.endTime;
    return s;
  }
}
