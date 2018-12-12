package problems.tsp_chaining.domain;


import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public class Visit implements Standstill{

    private Location location;

    private Standstill previousStandstill;

    @PlanningVariable(valueRangeProviderRefs = {"visitRange"},
            graphType = PlanningVariableGraphType.CHAINED)
    public Standstill getPreviousStandstill() {
        return previousStandstill;
    }

    public void setPreviousStandstill(Standstill previousStandstill) {
        this.previousStandstill = previousStandstill;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public double getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill.getLocation(), DistanceType.STRAIGHT_LINE);
    }
}
