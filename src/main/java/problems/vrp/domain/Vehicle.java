package problems.vrp.domain;

import org.optaplanner.core.api.domain.lookup.PlanningId;

public class Vehicle implements Standstill{

  @PlanningId
  private String planningId;

  private int capacity;
  private Depot depot;

  // shadow variable
  private Customer nextCustomer;

  public Vehicle () {

  }

  public Vehicle(String planningId) {
    this.planningId = planningId;
  }

  public int getCapacity() {
    return capacity;
  }
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Depot getDepot() {
    return depot;
  }

  public void setDepot(Depot depot) {
    this.depot = depot;
  }

  @Override
  public Customer getNextCustomer() {
    return nextCustomer;
  }


  public void setNextCustomer(Customer nextCustomer) {
    this.nextCustomer = nextCustomer;
  }


  @Override
  public Location getLocation() {
    return depot.getLocation();
  }

  @Override
  public double getDistanceTo(Standstill standstill) {
    return depot.getDistanceTo(standstill);
  }

  @Override
  public Vehicle getVehicle() {
    return this;
  }

  public String getPlanningId() {
    return planningId;
  }

  public void setPlanningId(String planningId) {
    this.planningId = planningId;
  }
}
