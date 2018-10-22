package problems.tsp.domain;


public class Location {

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

}
