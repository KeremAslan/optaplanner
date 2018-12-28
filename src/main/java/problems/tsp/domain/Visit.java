package problems.tsp.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.*;

import java.io.Serializable;

@PlanningEntity
public class Visit implements Standstill, Serializable {


    private Integer id;

    private Location location;
    private Integer position;

    private Visit nextVisit;


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
            return previousStandstill.getDistanceTo(this);
//                previousStandstill.getLocation().getDistanceTo(this);
        }
    }

    @PlanningId
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CustomShadowVariable(variableListenerClass = PositionListener.class,
            sources = {@PlanningVariableReference(variableName = "previousStandstill")})
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public Visit getNextVisit() {
        return nextVisit;
    }

    @Override
    public void setNextVisit(Visit visit) {
        this.nextVisit = visit;
    }

    @Override
    public double getDistanceTo(Standstill standstill) {
        return location.getDistanceTo(standstill);
    }

    @Override
    public String toString() {
        return "Visit{" +
            ", location=" + location +
            ", previousStandstill=" + previousStandstill +
            '}';
    }
}
