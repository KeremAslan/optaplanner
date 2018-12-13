package problems.tsp.domain;


import java.io.Serializable;

public class Location implements Serializable {

    private int id;
    private double longitude;
    private double latitude;

    public Location(int id, double longitude, double latitude){
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                '}';
    }

    public double getDistanceTo(Standstill standstill) {
        return
//            Math.round(
            Math.sqrt(
                Math.pow(latitude-standstill.getLocation().getLatitude(), 2)
            +  Math.pow(longitude-standstill.getLocation().getLongitude(),2));
    }
}
