package problems.vrp.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftdouble.HardSoftDoubleScore;

import java.io.Serializable;
import java.util.List;


@PlanningSolution
public class VrpSolution implements Serializable {

  private List<Vehicle> vehicles;

  private List<Customer> customers;

  private List<Location> locations;

  private List<Depot> depotList;

  private HardSoftDoubleScore score ;

  public VrpSolution() {

  }

  @PlanningEntityCollectionProperty
  @ValueRangeProvider(id = "vehicleRange")
  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }


  @PlanningEntityCollectionProperty
  @ValueRangeProvider(id = "customerRange")
  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  @ProblemFactProperty
  public List<Depot> getDepotList() {
    return depotList;
  }

  public void setDepotList(List<Depot> depotList) {
    this.depotList = depotList;
  }

  @ProblemFactCollectionProperty
  public List<Location> getLocations() {
    return locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }


  @PlanningScore
  public HardSoftDoubleScore getScore() {
    return score;
  }

  public void setScore(HardSoftDoubleScore score) {
    this.score = score;
  }
}
