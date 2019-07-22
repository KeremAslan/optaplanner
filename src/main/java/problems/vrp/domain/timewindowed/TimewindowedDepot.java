package problems.vrp.domain.timewindowed;

import problems.vrp.domain.Depot;
import problems.vrp.domain.Location;

public class TimewindowedDepot extends Depot {

  private long startTime;
  private long endTime;


  public TimewindowedDepot(Location location, long startTime, long endTime) {
    super(location);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public long getStartTime() {
    return startTime;
  }

  @Deprecated
  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public long getEndTime() {
    return endTime;
  }

  @Deprecated
  public void setEndTime(long endTime) {
    this.endTime = endTime;
  }
}
