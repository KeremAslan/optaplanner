package problems.vrp.domain.timewindowed;

import problems.vrp.domain.Vehicle;

public class TimewindowedVehicle extends Vehicle {

  private long startTime;
  private long endTime;

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public long getEndTime() {
    return endTime;
  }

  public void setEndTime(long endTime) {
    this.endTime = endTime;
  }
}
