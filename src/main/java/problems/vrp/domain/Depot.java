package problems.vrp.domain;

public class Depot {

  private Location location;



  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public double getDistanceTo(Standstill standstill) {
    return location.getDistanceTo(standstill.getLocation());
  }

  @Override
  public String toString() {
    return "Depot{" +
        "location=" + location +
        '}';
  }
}
