package problems.tsp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;

public class Location {

    private int id;
    private double longitude;
    private double latitude;

    public Location(int id, double longitude, double latitude){
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }


}
