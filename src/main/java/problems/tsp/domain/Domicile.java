package problems.tsp.domain;


import org.optaplanner.core.api.domain.lookup.PlanningId;

import java.io.Serializable;

/**
 * A domicile represents the home of a "salesman" and is an "anchor" in a list of chain.
 * Therefore it has no previousStandstill.
 */
public class Domicile implements Standstill, Serializable {

  private Integer id;

  private Location location;


  @Override
  public Location getLocation() {
    return location;
  }

 @Override
  public double getDistanceTo(Standstill standstill) {
    return location.getDistanceTo(standstill);
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return "Domicile{" +
        "location=" + location +
        '}';
  }

    @PlanningId
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
