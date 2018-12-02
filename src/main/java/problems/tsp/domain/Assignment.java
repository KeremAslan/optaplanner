package problems.tsp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Assignment {


    private int position;

    //Plannings Varriable
    private Location location;

    public Assignment(){

    }

    public Assignment(int position){
        this.position = position;
    }


    @PlanningVariable(valueRangeProviderRefs = "locations")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "position=" + position +
                ", location=" + location +
                '}';
    }

    public int getPosition() {
        return position;
    }
}
