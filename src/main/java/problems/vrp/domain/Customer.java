package problems.vrp.domain;


import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public class Customer implements Standstill{


  private Integer id;
  private Location location;
  private int demand;


  // Planning variable
  private Standstill previousLocation;

  // Shadow variables
  private Customer nextCustomer;
  private Vehicle vehicle;

  public Customer () {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public int getDemand() {
    return demand;
  }

  public void setDemand(int demand) {
    this.demand = demand;
  }

  @PlanningVariable(valueRangeProviderRefs = {"vehicleRange", "customerRange"}, graphType = PlanningVariableGraphType.CHAINED)
  public Standstill getPreviousStandstill() {
    return previousLocation;
  }

  public void setPreviousStandstill(Standstill previousLocation) {
    this.previousLocation = previousLocation;
  }

  @Override
  @AnchorShadowVariable(sourceVariableName = "previousStandstill")
  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  @Override
  public double getDistanceTo(Standstill standstill) {
    return location.getDistanceTo(standstill.getLocation());
  }

  public double getDistanceFromPreviousStandstill() {
    if(previousLocation != null) {
      return getDistanceFrom(previousLocation);
    } else {
      throw new IllegalStateException("This method must not be called when the previousStandstill ("
          + previousLocation + ") is not initialized yet.");
    }
  }

  public double getDistanceFrom(Standstill standstill) {
    return location.getDistanceTo(standstill.getLocation());
  }

  @Override
  public Customer getNextCustomer() {
    return nextCustomer;
  }

  public void setNextCustomer(Customer nextCustomer) {
    this.nextCustomer = nextCustomer;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "location=" + location +
        ", demand=" + demand +
        '}';
  }
}


