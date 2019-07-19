package problems.vrp.domain.timewindowed;

import problems.vrp.domain.Depot;

public class TimewindowedDepot extends Depot {

  private long startTime;
  private long endTime;


  public TimewindowedDepot(long startTime, long endTime) {
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
