package problems.vrp.domain.timewindowed;

import problems.vrp.domain.Customer;
import problems.vrp.domain.Location;

public class TimewindowedCustomer extends Customer {

  private long startTime;
  private long endTime;
  private long serviceTime;


  public TimewindowedCustomer(Location location, long startTime, long endTime, long serviceTime, int demand) {
    super.setLocation(location);
    super.setDemand(demand);
    this.startTime = startTime;
    this.endTime = endTime;
    this.serviceTime = serviceTime;
  }

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

  public long getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(long serviceTime) {
    this.serviceTime = serviceTime;
  }
}
