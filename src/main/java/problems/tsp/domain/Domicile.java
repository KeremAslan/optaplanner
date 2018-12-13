package problems.tsp.domain;


import java.io.Serializable;

/**
 * A domicile represents the home of a "salesman" and is an "anchor" in a list of chain.
 * Therefore it has no previousStandstill.
 */
public class Domicile implements Standstill, Serializable {


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
}
