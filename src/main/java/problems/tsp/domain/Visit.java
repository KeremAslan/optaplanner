package problems.tsp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

import java.io.Serializable;

@PlanningEntity
public class Visit implements Standstill, Serializable {

    private Location location;

    //Planning variable: changes during planning. (Location is set only once!)
    private Standstill previousStandstill;

    public Visit(){

    }

    @Override
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    // Note that this can return either an anchor or a visit!
    @PlanningVariable(valueRangeProviderRefs = {"domicileRange", "visitRange"}, graphType = PlanningVariableGraphType.CHAINED)
    public Standstill getPreviousStandstill() {
        return previousStandstill;
    }

    public void setPreviousStandstill(Standstill previousStandstill) {
        this.previousStandstill = previousStandstill;
    }


    public double getDistanceFromPreviousStandstill() {
        if(previousStandstill == null) {
            return 0;
        } else {
            return previousStandstill.getLocation().getDistanceTo(this);
        }
    }

    @Override
    public double getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill);
    }

    @Override
    public String toString() {
        return "Visit{" +
            ", location=" + location +
            '}';
    }
}
