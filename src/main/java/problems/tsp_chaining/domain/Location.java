package problems.tsp_chaining.domain;


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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                '}';
    }

    public double getDistanceTo(Location location, DistanceType distanceType) {
        if (DistanceType.STRAIGHT_LINE == distanceType) {
            return Math.sqrt(Math.pow(location.latitude-this.latitude, 2) + Math.pow(location.longitude-this.longitude, 2));
        } else {
            throw new RuntimeException("Unspecified distance type");
        }
    }
}
