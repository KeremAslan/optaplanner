package problems.vrp.domain.timewindowed;

import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;
import problems.vrp.domain.Customer;
import problems.vrp.domain.Location;
import problems.vrp.domain.timewindowed.solver.ArrivalTimeUpdatingVariableListener;

public class TimeWindowedCustomer extends Customer {

  private long startTime;
  private long endTime;
  private long serviceTime;

  private Long arrivalTime;


  public TimeWindowedCustomer(Location location, long startTime, long endTime, long serviceTime, int demand) {
    super.setLocation(location);
    super.setDemand(demand);
    this.startTime = startTime;
    this.endTime = endTime;
    this.serviceTime = serviceTime;
  }

  /**
   * @return a positive number, the time multiplied by 1000 to avoid floating point arithmetic rounding errors
   */
  @CustomShadowVariable(variableListenerClass = ArrivalTimeUpdatingVariableListener.class,
      // Arguable, to adhere to API specs (although this works), nextCustomer should also be a source,
      // because this shadow must be triggered after nextCustomer (but there is no need to be triggered by nextCustomer)
      sources = {@PlanningVariableReference(variableName = "previousStandstill")})
  public Long getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(Long arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public Long getDepartureTime() {
    if (arrivalTime == null) {
      return null;
    }
    return Math.max(arrivalTime, startTime) + serviceTime;
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
