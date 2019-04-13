package problems.tsp.domain;


import java.io.Serializable;

public class Location implements Serializable {

    private int id;
    private double longitude;
    private double latitude;
    private boolean isPrime;


    public Location(int id, double longitude, double latitude){
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isPrime = isPrime(id);
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

    private boolean isPrime(int locationId) {

        if (locationId == 2 || locationId == 3) {
            return true;
        }

        if (locationId % 2  == 0 || locationId < 2) {
            return true;
        }

        for (int i=3; i < Math.sqrt(locationId); i += 2) {
            if (locationId % i == 0) {
                return false;
            }
        }
        return true;
    }


    public boolean isPrime() {
        return isPrime;
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
                Math.pow(latitude -standstill.getLocation().getLatitude(), 2)
            +  Math.pow(longitude-standstill.getLocation().getLongitude(),2));
    }
}
