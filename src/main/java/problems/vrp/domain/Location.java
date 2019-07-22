package problems.vrp.domain;


public class Location {

  private int id;
  private double lat;
  private double lon;

  public Location (int id, double lat, double lon) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
  }

  public int getId() {
    return id;
  }

  public double getLat() {
    return lat;
  }

  public double getLon() {
    return lon;
  }

  @Override
  public String toString() {
    return "Location{" +
        "id=" + id +
        '}';
  }

  public double getDistanceTo(Location location) {
    return

//            Math.round(
        Math.sqrt(
            Math.pow(lat -location.getLat(), 2)
                +  Math.pow(lon-location.getLon(),2));
  }
}
